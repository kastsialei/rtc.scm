package rtc.scm.executor;

import com.intellij.CommonBundle;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.Nullable;
import scm.ScmCommand;
import scm.VersionScmCommand;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 23.05.14
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
public class ScmCommandLineExecutor extends AbstractExecutor {
  private final Project project;
  private final ScmCommand command;
  private OSProcessHandler myProcessHandler;


  public ScmCommandLineExecutor(Project project, String caption, ScmConsole console, ScmCommand command) {
    super(caption, console);
    this.project = project;
    this.command = command;


  }

  public ScmCommandLineExecutor(String caption, ScmConsole console, Project project) {
    super(caption, console);
    this.project = project;
    this.command = new VersionScmCommand();
  }

  @Override
  public boolean execute(final ProgressIndicator indicator) {
    displayProgress();

    try {

      myProcessHandler =
          new OSProcessHandler(createProcess(CommandLineUtils.prepareGeneralCommandLine(project, command)) ) {
            public void notifyTextAvailable(String text, Key outputType) {
              super.notifyTextAvailable(text, outputType);
              updateProgress(indicator, text);
            }

          };

      myConsole.attachToProcess(myProcessHandler);
    }
    catch (ExecutionException e) {
      myConsole.systemMessage(ScmBundle.message("external.startup.failed", e.getMessage()));
      return false;
    }

    start();
    readProcessOutput();
    stop();

    return printExitSummary();
  }

  void stop() {
    if (myProcessHandler != null) {
      myProcessHandler.destroyProcess();
      myProcessHandler.waitFor();
      setExitCode(myProcessHandler.getProcess().exitValue());
    }
    super.stop();
  }

  private void readProcessOutput() {
    myProcessHandler.startNotify();
    myProcessHandler.waitFor();
  }

  private void updateProgress(@Nullable final ProgressIndicator indicator, final String text) {
    if (indicator != null) {
      if (indicator.isCanceled()) {
        if (!isCancelled()) {
          ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
              cancel();
            }
          });
        }
      }
//      if (text.matches(PHASE_INFO_REGEXP)) {
//        indicator.setText2(text.substring(INFO_PREFIX_SIZE));
//      }
    }
  }

  public static Process createProcess(GeneralCommandLine generalCommandLine) throws ExecutionException {
    return generalCommandLine.createProcess();
  }
}
