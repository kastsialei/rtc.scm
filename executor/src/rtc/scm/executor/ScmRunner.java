package rtc.scm.executor;

import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 04.10.14
 * Time: 22:23
 * To change this template use File | Settings | File Templates.
 */
public class ScmRunner {

  private final Project myProject;

  public static ScmRunner getInstance(Project project) {
    return ServiceManager.getService(project, ScmRunner.class);
  }

  public ScmRunner(final Project project) {
    myProject = project;
  }

  public void run() {
    FileDocumentManager.getInstance().saveAllDocuments();
    final ScmConsole scmConsole = createScmConsole();

    final AbstractExecutor executor = new ScmCommandLineExecutor("Execution",scmConsole, myProject);
    try {
      ProgressManager.getInstance().run(new Task.Backgroundable(myProject, executor.getCaption(), true) {
        public void run(@NotNull ProgressIndicator indicator) {
          try {
            try {
              if (executor.execute(indicator)) {
//                if (onComplete != null) onComplete.run();
              }
            }
            catch (ProcessCanceledException ignore) {
            }

          }
          finally {
            scmConsole.finish();
          }
        }

        @Nullable
        public NotificationInfo getNotificationInfo() {
          return new NotificationInfo("Scm Runner", "Scm Command Finished", "");
        }

        public boolean shouldStartInBackground() {
          return false;
        }

        public void processSentToBackground() {

        }

        public void processRestoredToForeground() {

        }
      });
    }
    catch (Exception e) {
      scmConsole.finish();
      scmConsole.systemMessage(e.toString());
      e.printStackTrace();
    }
  }

  private ScmConsole createScmConsole() {
//    TextConsoleBuilder builder = TextConsoleBuilderFactory.getInstance().createBuilder(myProject);
    return new ScmConsole("Scm Console", myProject);
  }


}
