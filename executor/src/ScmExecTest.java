import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import rtc.scm.executor.ScmCommandLineExecutor;
import rtc.scm.executor.ScmConsole;
import rtc.scm.executor.ScmRunner;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 04.10.14
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class ScmExecTest extends AnAction {
  public void actionPerformed(AnActionEvent e) {
    Project project = e.getProject();
    ScmRunner.getInstance(project).run();
  }
}
