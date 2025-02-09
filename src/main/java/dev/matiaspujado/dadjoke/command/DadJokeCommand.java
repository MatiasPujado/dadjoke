package dev.matiaspujado.dadjoke.command;

import dev.matiaspujado.dadjoke.model.CustomClient;
import dev.matiaspujado.dadjoke.model.CustomResponse;
import dev.matiaspujado.dadjoke.model.Result;
import dev.matiaspujado.dadjoke.model.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class DadJokeCommand {

  private static final Logger log = LoggerFactory.getLogger(DadJokeCommand.class);
  private final CustomClient customClient;


  public DadJokeCommand(CustomClient customClient) {
    this.customClient = customClient;
  }

  @ShellMethod(key = "random", value = "Prints a random dad joke.")
  public String getRandomJoke() {
    CustomResponse customResponse = customClient.random();
    logResponse(customResponse);
    return customResponse.joke();
  }

  @ShellMethod(key = "joke", value = "Prints a dad joke fetched by ID.")
  public String getSpecificJoke(
    @Option @ShellOption(defaultValue = "M7wPC5wPKBd") String id
  ) {
    CustomResponse customResponse = customClient.specificJoke(id);
    logResponse(customResponse);
    return customResponse.joke();
  }

  @ShellMethod(key = "search", value = "Searches for a dad joke.")
  public String searchJoke(
    @Option @ShellOption(defaultValue = "1") String page,
    @Option @ShellOption(defaultValue = "20") String limit,
    @Option @ShellOption(defaultValue = "list all jokes", arity = 10) String term
  ) {
    SearchResponse response = customClient.search(page, limit, term);
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

  private void logResponse(CustomResponse customResponse) {
    log.debug("Joke ID: {}", customResponse.id());
    log.debug("Received joke: {}", customResponse.joke());
    log.debug("Status: {}", customResponse.status());
  }
}
