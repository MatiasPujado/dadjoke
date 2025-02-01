package dev.matiaspujado.dadjoke.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class HelloCommand {

  @ShellMethod(key = "hello", value = "Prints a hello message.")
  public String hello() {
    return "Hello, World!";
  }

  @ShellMethod(key = "goodbye", value = "Prints a goodbye message.")
  public String goodbye() {
    return "Goodbye, World!";
  }
}
