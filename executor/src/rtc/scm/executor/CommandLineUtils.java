package rtc.scm.executor;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.project.Project;
import scm.ScmCommand;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 23.05.14
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
public class CommandLineUtils {
  public static GeneralCommandLine prepareGeneralCommandLine(Project project, ScmCommand command) {
    if(project == null || command == null) throw new IllegalArgumentException();
    return null;
  }
}
