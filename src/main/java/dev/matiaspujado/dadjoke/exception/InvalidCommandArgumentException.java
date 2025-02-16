package dev.matiaspujado.dadjoke.exception;

import static dev.matiaspujado.dadjoke.enums.ExceptionMessages.INVALID_COMMAND_ARGUMENT;

/**
 * Exception thrown when an invalid command argument is encountered in the shell.
 */
public class InvalidCommandArgumentException extends BaseCustomShellException {

  /**
   * Constructs a new InvalidCommandArgumentException with a default message.
   */
  public InvalidCommandArgumentException() {
    super(INVALID_COMMAND_ARGUMENT.getMessage(), 2);
  }

  /**
   * Constructs a new InvalidCommandArgumentException with the specified detail message.
   *
   * @param message the detail message
   */
  public InvalidCommandArgumentException(String message) {
    super(message, 2);
  }

  /**
   * Constructs a new InvalidCommandArgumentException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public InvalidCommandArgumentException(Throwable cause) {
    super(cause, 2);
  }

  /**
   * Constructs a new InvalidCommandArgumentException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of the exception
   */
  public InvalidCommandArgumentException(String message, Throwable cause) {
    super(message, cause, 2);
  }

  /**
   * Constructs a new InvalidCommandArgumentException with the specified cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param cause the cause of the exception
   * @param enableSuppression whether or not suppression is enabled or disabled
   * @param writableStackTrace whether or not the stack trace should be writable
   */
  public InvalidCommandArgumentException(Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(INVALID_COMMAND_ARGUMENT.getMessage(), cause, enableSuppression, writableStackTrace, 2);
  }

  /**
   * Constructs a new InvalidCommandArgumentException with the specified detail message, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message the detail message
   * @param cause the cause of the exception
   * @param enableSuppression whether or not suppression is enabled or disabled
   * @param writableStackTrace whether or not the stack trace should be writable
   */
  public InvalidCommandArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace, 2);
  }

  /**
   * Factory method to create an InvalidCommandArgumentException for a specific argument.
   *
   * @param argument the invalid argument
   * @return a new InvalidCommandArgumentException with a message including the invalid argument
   */
  public static InvalidCommandArgumentException forArgument(String argument) {
    return new InvalidCommandArgumentException(INVALID_COMMAND_ARGUMENT.getMessage() + argument);
  }
}
