package dev.matiaspujado.dadjoke.enums;

  /**
   * Enum representing various exception messages used in the application.
   */
  public enum ExceptionMessages {

    /**
     * Message for command not found exception.
     */
    COMMAND_NOT_FOUND("Command not found "),

    /**
     * Message for joke not found exception.
     */
    JOKE_NOT_FOUND("Joke not found "),

    /**
     * Message for joke not found for a specific ID exception.
     */
    JOKE_NOT_FOUND_FOR_ID("Joke not found for ID: "),

    /**
     * Message for joke not found for a specific term exception.
     */
    JOKE_NOT_FOUND_FOR_TERM("Joke not found for term: "),

    /**
     * Message for joke ID cannot be null or empty exception.
     */
    JOKE_ID_CANNOT_BE_NULL_OR_EMPTY("Joke ID cannot be null or empty "),

    /**
     * Message for unexpected error occurred exception.
     */
    UNEXPECTED_ERROR_OCCURRED("An unexpected error occurred "),

    /**
     * Message for invalid command argument exception.
     */
    INVALID_COMMAND_ARGUMENT("Invalid command argument ");

    private final String message;

    /**
     * Constructor for ExceptionMessages enum.
     *
     * @param message the exception message.
     */
    ExceptionMessages(String message) {
      this.message = message;
    }

    /**
     * Gets the exception message.
     *
     * @return the exception message.
     */
    public String getMessage() {
      return message;
    }
  }
