package dev.matiaspujado.dadjoke.exception;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.shell.standard.ShellComponent;

import java.util.Arrays;
import java.util.StringJoiner;

@ShellComponent
public class BaseCustomShellException extends RuntimeException implements ExitCodeGenerator {

  private int exitCode = 0;

  public BaseCustomShellException() {
    super();
  }

  public BaseCustomShellException(String message, int exitCode) {
    super(message);
    this.exitCode = exitCode;
  }

  public BaseCustomShellException(Throwable cause, int exitCode) {
    super(cause);
    this.exitCode = exitCode;
  }

  public BaseCustomShellException(String message, Throwable cause, int exitCode) {
    super(message, cause);
    this.exitCode = exitCode;
  }

  public BaseCustomShellException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int exitCode) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.exitCode = exitCode;
  }

  @Override
  public int getExitCode() {
    return exitCode;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BaseCustomShellException.class.getSimpleName() + "[", "]")
             .add("exitCode: " + getExitCode())
             .add("class: " + getClass())
             .add("message: " + getMessage())
             .add("cause: " + getCause())
             .add("stackTrace: " + Arrays.toString(getStackTrace()))
             .toString();
  }
}
