package dev.matiaspujado.dadjoke.command;

import dev.matiaspujado.dadjoke.exception.InvalidCommandArgumentException;
import dev.matiaspujado.dadjoke.exception.JokeNotFoundException;
import dev.matiaspujado.dadjoke.model.CustomClient;
import dev.matiaspujado.dadjoke.model.CustomResponse;
import dev.matiaspujado.dadjoke.model.Result;
import dev.matiaspujado.dadjoke.model.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.StringJoiner;

  /**
   * Shell component for Dad Joke commands.
   */
  @ShellComponent
  @ShellCommandGroup(value = "Dad Joke Commands")
  public class DadJokeCommand {

    private static final Logger log = LoggerFactory.getLogger(DadJokeCommand.class);
    private final CustomClient customClient;

    /**
     * Constructs a new DadJokeCommand with the specified CustomClient.
     *
     * @param customClient the custom client to use for fetching jokes.
     */
    public DadJokeCommand(CustomClient customClient) {
      this.customClient = customClient;
    }

    /**
     * Prints a random dad joke.
     *
     * @return the random dad joke.
     */
    @ShellMethod(key = "random", value = "Prints a random dad joke.")
    public String getRandomJoke() {
      CustomResponse customResponse = customClient.random();
      if (customResponse == null) {
        throw new JokeNotFoundException();
      }
      logResponse(customResponse);
      return customResponse.joke();
    }

    /**
     * Prints a dad joke fetched by ID.
     *
     * @param id the ID of the joke to fetch.
     * @return the dad joke.
     * @throws Throwable if the ID is invalid or the joke is not found.
     */
    @ShellMethod(key = "joke", value = "Prints a dad joke fetched by ID.")
    public String getSpecificJoke(@Option @ShellOption(defaultValue = "") String id) throws Throwable {
      if (id == null || id.isEmpty()) {
        throw InvalidCommandArgumentException.forArgument(id);
      }
      CustomResponse customResponse = customClient.specificJoke(id);
      if (customResponse == null) {
        throw JokeNotFoundException.forId(id);
      }
      logResponse(customResponse);
      return customResponse.joke();
    }

    /**
     * Searches for a dad joke.
     *
     * @param page the page number to fetch.
     * @param limit the number of jokes per page.
     * @param term the search term.
     * @return the search results.
     */
    @ShellMethod(key = "search", value = "Searches for a dad joke.")
    public String searchJoke(
      @Option @ShellOption(defaultValue = "1") String page,
      @Option @ShellOption(defaultValue = "20") String limit,
      @Option @ShellOption(defaultValue = "list all jokes", arity = 10) String term
    ) {
      SearchResponse response = customClient.search(page, limit, term);
      if (response == null) {
        throw JokeNotFoundException.forTerm(term);
      }
      StringBuilder resultBuilder = new StringBuilder();
      resultBuilder
        .append("------------------------------------------------------------------------------------------\n")
        .append("Page: ").append(response.current_page()).append("\n")
        .append("Total jokes: ").append(response.total_jokes()).append("\n")
        .append("Total pages: ").append(response.total_pages()).append("\n")
        .append("Search term: ").append(
          response.search_term().equals("list all jokes") ? "list all jokes (default)" : response.search_term()
        ).append("\n")
        .append("------------------------------------------------------------------------------------------\n");

      for (Result result : response.results()) {
        resultBuilder
          .append(result.id()).append(":  ")
          .append(result.joke().trim()).append("\n");
      }

      return resultBuilder.toString();
    }

    /**
     * Logs the response details if debug is enabled.
     *
     * @param customResponse the response to log.
     */
    private void logResponse(CustomResponse customResponse) {
      if (log.isDebugEnabled()) {
        log.debug("Joke ID: {}", customResponse.id());
        log.debug("Received joke: {}", customResponse.joke());
        log.debug("Status: {}", customResponse.status());
      }
    }

    /**
     * Returns a string representation of the DadJokeCommand.
     *
     * @return a string representation of the DadJokeCommand.
     */
    @Override
    public String toString() {
      return new StringJoiner(", ", DadJokeCommand.class.getSimpleName() + "[", "]")
               .add("customClient=" + customClient)
               .toString();
    }
  }
