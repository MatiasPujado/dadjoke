package dev.matiaspujado.dadjoke.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.stereotype.Component;

import static dev.matiaspujado.dadjoke.enums.ExceptionMessages.UNEXPECTED_ERROR_OCCURRED;

/**
 * Component that resolves exceptions thrown during command execution in the shell.
 */
@Component
public class ShellExceptionResolver implements CommandExceptionResolver {

  private static final Logger log = LoggerFactory.getLogger(ShellExceptionResolver.class);

  /**
   * Constructs a new ShellExceptionResolver.
   */
  public ShellExceptionResolver() {
    super();
  }

  /**
   * Resolves the given exception and returns a CommandHandlingResult.
   *
   * @param exception the exception to resolve.
   * @return the result of handling the exception.
   */
  @Override
  public CommandHandlingResult resolve(Exception exception) {
    CommandRegistration.builder()
      .withExitCode()
      .map(BaseCustomShellException.class, 3)
      .map(throwable -> {
        if (throwable instanceof BaseCustomShellException shellException) {
          return shellException.getExitCode();
        }
        return 0;
      })
      .and()
      .build();

    if (log.isDebugEnabled()) {
      log.debug("An exception occurred while executing a command: {}", exception.getMessage());
    }

    return CommandHandlingResult.of(UNEXPECTED_ERROR_OCCURRED.getMessage() + exception.getMessage(), 1);
  }
}
