package dev.matiaspujado.dadjoke.exception;

import static dev.matiaspujado.dadjoke.enums.ExceptionMessages.COMMAND_NOT_FOUND;

/**
 * Exception thrown when a command is not found in the shell.
 */
public class CommandNotFoundException extends BaseCustomShellException {

  /**
   * Constructs a new CommandNotFoundException with a default message.
   */
  public CommandNotFoundException() {
    super(COMMAND_NOT_FOUND.getMessage(), 1);
  }

  /**
   * Constructs a new CommandNotFoundException with the specified detail message.
   *
   * @param message the detail message.
   */
  public CommandNotFoundException(String message) {
    super(message, 1);
  }

  /**
   * Constructs a new CommandNotFoundException with the specified cause.
   *
   * @param cause the cause of the exception.
   */
  public CommandNotFoundException(Throwable cause) {
    super(cause, 1);
  }

  /**
   * Constructs a new CommandNotFoundException with the specified detail message and cause.
   *
   * @param message the detail message.
   * @param cause   the cause of the exception.
   */
  public CommandNotFoundException(String message, Throwable cause) {
    super(message, cause, 1);
  }

  /**
   * Constructs a new CommandNotFoundException with the specified cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param cause              the cause of the exception.
   * @param enableSuppression  whether or not suppression is enabled or disabled.
   * @param writableStackTrace whether or not the stack trace should be writable.
   */
  public CommandNotFoundException(Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(COMMAND_NOT_FOUND.getMessage(), cause, enableSuppression, writableStackTrace, 1);
  }

  /**
   * Constructs a new CommandNotFoundException with the specified detail message, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message            the detail message.
   * @param cause              the cause of the exception.
   * @param enableSuppression  whether or not suppression is enabled or disabled.
   * @param writableStackTrace whether or not the stack trace should be writable.
   */
  public CommandNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace, 1);
  }
}
