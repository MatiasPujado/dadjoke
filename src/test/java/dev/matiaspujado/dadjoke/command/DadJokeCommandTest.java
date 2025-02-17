package dev.matiaspujado.dadjoke.command;

import dev.matiaspujado.dadjoke.exception.InvalidCommandArgumentException;
import dev.matiaspujado.dadjoke.exception.JokeNotFoundException;
import dev.matiaspujado.dadjoke.model.CustomClient;
import dev.matiaspujado.dadjoke.model.CustomResponse;
import dev.matiaspujado.dadjoke.model.Result;
import dev.matiaspujado.dadjoke.model.SearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class DadJokeCommandTest {

  @Mock
  private Logger loggerMock;
  @Mock
  private CustomClient customClientMock;
  @InjectMocks
  private DadJokeCommand dadJokeCommand;

  public static Stream<Arguments> searchJokeArguments() {
    return Stream.of(
      Arguments.of("list all jokes"),
      Arguments.of("cat")
    );
  }

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createInstance() {
    dadJokeCommand = new DadJokeCommand(customClientMock);
    assertNotNull(dadJokeCommand);
  }

  @Test
  void getRandomJoke_returnsJoke() {
    CustomResponse customResponse = new CustomResponse("1", "This is a dad joke.", 200);
    dadJokeCommand = new DadJokeCommand(customClientMock);

    when(customClientMock.random()).thenReturn(customResponse);

    String joke = dadJokeCommand.getRandomJoke();

    assertAll(() -> assertNotNull(customResponse),
      () -> assertNotNull(joke),
      () -> assertFalse(joke.isBlank()),
      () -> assertEquals("This is a dad joke.", joke));
  }

  @Test
  void logResponse_logsDetailsWhenDebugIsEnabled() throws Exception {
    CustomResponse customResponse = new CustomResponse("1", "This is a dad joke.", 200);
    dadJokeCommand = new DadJokeCommand(customClientMock);

    Field logSetPrivate = DadJokeCommand.class.getDeclaredField("log");
    logSetPrivate.setAccessible(true);
    logSetPrivate.set(dadJokeCommand, loggerMock);

    when(loggerMock.isDebugEnabled()).thenReturn(true);

    when(customClientMock.random()).thenReturn(customResponse);

    String joke = dadJokeCommand.getRandomJoke();

    assertAll(() -> assertNotNull(customResponse),
      () -> assertNotNull(joke),
      () -> assertFalse(joke.isBlank()),
      () -> assertEquals("This is a dad joke.", joke));
  }

  @Test
  void getRandomJoke_throwsJokeNotFoundException() {
    when(customClientMock.random()).thenReturn(null);
    assertThrows(JokeNotFoundException.class, () -> dadJokeCommand.getRandomJoke());
  }

  @Test
  void getSpecificJoke_returnsJokeByID() {
    CustomResponse customResponse = new CustomResponse("1", "This is a dad joke.", 200);
    dadJokeCommand = new DadJokeCommand(customClientMock);

    when(customClientMock.specificJoke("1")).thenReturn(customResponse);

    String joke = dadJokeCommand.getSpecificJoke("1");

    assertAll(() -> assertNotNull(customResponse),
      () -> assertNotNull(joke),
      () -> assertFalse(joke.isBlank()),
      () -> assertEquals("This is a dad joke.", joke));
  }

  @Test
  void getSpecificJoke_throwsInvalidCommandArgumentException_whenIdIsNull() {
    CustomResponse customResponse = new CustomResponse(null, "This is a dad joke.", 200);
    dadJokeCommand = new DadJokeCommand(customClientMock);

    when(customClientMock.specificJoke(null)).thenReturn(customResponse);

    assertThrows(InvalidCommandArgumentException.class, () -> dadJokeCommand.getSpecificJoke(null));
  }

  @Test
  void getSpecificJoke_throwsInvalidCommandArgumentException_whenIdIsEmpty() {
    CustomResponse customResponse = new CustomResponse("", "This is a dad joke.", 200);
    dadJokeCommand = new DadJokeCommand(customClientMock);

    when(customClientMock.specificJoke("")).thenReturn(customResponse);

    assertThrows(InvalidCommandArgumentException.class, () -> dadJokeCommand.getSpecificJoke(""));
  }

  @Test
  void getSpecificJoke_throwsJokeNotFoundException() {
    when(customClientMock.specificJoke("1")).thenReturn(null);
    assertThrows(JokeNotFoundException.class, () -> dadJokeCommand.getSpecificJoke("1"));
  }

  @ParameterizedTest
  @MethodSource("searchJokeArguments")
  void searchJoke_returnsAllJokes(String term) {
    Result result1 = new Result("1", "This is a dad joke.");
    Result result2 = new Result("2", "This is a dad joke.");
    Result result3 = new Result("3", "This is a dad joke.");
    List<Result> resultsList = List.of(result1, result2, result3);
    SearchResponse searchResponse = new SearchResponse(1,1,1,1,resultsList,term,200,1,1);
    dadJokeCommand = new DadJokeCommand(customClientMock);

    when(customClientMock.search("1","20",term)).thenReturn(searchResponse);

    String joke = dadJokeCommand.searchJoke("1","20",term);

    assertAll(() -> assertNotNull(searchResponse),
      () -> assertNotNull(joke),
      () -> assertFalse(joke.isBlank()),
      () -> assertEquals("------------------------------------------------------------------------------------------\n" +
                           "Page: 1\n" +
                           "Total jokes: 1\n" +
                           "Total pages: 1\n" +
                           "Search term: " + (term.equals("list all jokes") ? term + " (default)\n" : term + "\n") +
                           "------------------------------------------------------------------------------------------\n" +
                           "1:  This is a dad joke.\n" +
                           "2:  This is a dad joke.\n" +
                           "3:  This is a dad joke.\n", joke));
  }

  @Test
  void searchJoke_throwsJokeNotFoundException() {
    when(customClientMock.search("1","20","list all jokes")).thenReturn(null);
    assertThrows(JokeNotFoundException.class, () -> dadJokeCommand.searchJoke("1","20","list all jokes"));
  }
}
