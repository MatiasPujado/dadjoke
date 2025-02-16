package dev.matiaspujado.dadjoke.exception;

import static dev.matiaspujado.dadjoke.enums.ExceptionMessages.*;

/**
 * Exception thrown when a joke is not found.
 */
public class JokeNotFoundException extends BaseCustomShellException {

  /**
   * Constructs a new JokeNotFoundException with a default message.
   */
  public JokeNotFoundException() {
    super(JOKE_NOT_FOUND.getMessage(), 3);
  }

  /**
   * Constructs a new JokeNotFoundException with the specified detail message.
   *
   * @param message the detail message.
   */
  private JokeNotFoundException(String message) {
    super(message, 3);
  }

  /**
   * Constructs a new JokeNotFoundException with the specified cause.
   *
   * @param cause the cause of the exception.
   */
  public JokeNotFoundException(Throwable cause) {
    super(cause, 3);
  }

  /**
   * Constructs a new JokeNotFoundException with the specified joke ID and cause.
   *
   * @param id    the joke ID.
   * @param cause the cause of the exception.
   */
  public JokeNotFoundException(String id, Throwable cause) {
    super(JOKE_NOT_FOUND_FOR_ID.getMessage() + id, cause, 3);
  }

  /**
   * Constructs a new JokeNotFoundException with the specified cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param cause              the cause of the exception.
   * @param enableSuppression  whether or not suppression is enabled or disabled.
   * @param writableStackTrace whether or not the stack trace should be writable.
   */
  public JokeNotFoundException(Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(JOKE_NOT_FOUND.getMessage(), cause, enableSuppression, writableStackTrace, 3);
  }

  /**
   * Constructs a new JokeNotFoundException with the specified joke ID, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param id                 the joke ID.
   * @param cause              the cause of the exception.
   * @param enableSuppression  whether or not suppression is enabled or disabled.
   * @param writableStackTrace whether or not the stack trace should be writable.
   */
  public JokeNotFoundException(String id, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(JOKE_NOT_FOUND_FOR_ID.getMessage() + id, cause, enableSuppression, writableStackTrace, 3);
  }

  /**
   * Creates a new JokeNotFoundException for the specified joke ID.
   *
   * @param id the joke ID.
   * @return a new JokeNotFoundException.
   */
  public static JokeNotFoundException forId(String id) {
    Throwable cause = new IllegalArgumentException(JOKE_ID_CANNOT_BE_NULL_OR_EMPTY.getMessage());
    return new JokeNotFoundException(JOKE_NOT_FOUND_FOR_ID.getMessage() + id, cause);
  }

  /**
   * Creates a new JokeNotFoundException for the specified search term.
   *
   * @param term the search term.
   * @return a new JokeNotFoundException.
   */
  public static JokeNotFoundException forTerm(String term) {
    return new JokeNotFoundException(JOKE_NOT_FOUND_FOR_TERM.getMessage() + term);
  }
}
