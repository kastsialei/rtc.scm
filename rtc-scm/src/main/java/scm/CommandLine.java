package scm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 15.05.14
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public final class CommandLine {
  private final String command;
  private final List<String> parameters;

  public CommandLine(String command) {
    this.command = command;
    parameters = new LinkedList<String>();
  }

  public String getCommand() {
    return command;
  }

  public List<String> getParameters() {
    return Collections.unmodifiableList(parameters);
  }

  public void addParameter(String parameter) {
//    checkNotNull(parameter);
//    checkArgument(parameters.contains(parameter));
    parameters.add(parameter);
  }
}
