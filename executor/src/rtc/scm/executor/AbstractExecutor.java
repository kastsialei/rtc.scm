package rtc.scm.executor;

import com.intellij.CommonBundle;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 20.05.14
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractExecutor {
  private final String myCaption;
  protected ScmConsole myConsole;
  private String myAction;

  private boolean stopped = true;
  private boolean cancelled = false;
  private int exitCode = 0;

  public AbstractExecutor(String caption,
                          ScmConsole console) {
    myCaption = caption;
    myConsole = console;
  }

  public String getCaption() {
    return myCaption;
  }

  public ScmConsole getConsole() {
    return myConsole;
  }

  public void setAction(@Nullable final String action) {
    myAction = action;
  }

  public boolean isStopped() {
    return stopped;
  }

  void start() {
    stopped = false;
  }

  void stop() {
    stopped = true;
    myConsole.setOutputPaused(false);
  }

  boolean isCancelled() {
    return cancelled;
  }

  public void cancel() {
    cancelled = true;
    stop();
  }

  protected void setExitCode(int exitCode) {
    this.exitCode = exitCode;
  }

  void displayProgress() {
    final ProgressIndicator indicator = ProgressManager.getInstance().getProgressIndicator();
    if (indicator != null) {
      indicator.setText(MessageFormat.format("{0} {1}", myAction != null ? myAction : CommonBundle.message("scm.executor.running"),
          ""));
    }
  }

  protected boolean printExitSummary() {
    if (isCancelled()) {
      myConsole.systemMessage(CommonBundle.message("scm.executor.aborted"));
      return false;
    }
    else if (exitCode == 0) {
      myConsole.systemMessage(CommonBundle.message("scm.executor.finished"));
      return true;
    }
    else {
      myConsole
          .systemMessage(CommonBundle.message("scm.executor.exit", exitCode));
      return false;
    }
  }

  public abstract boolean execute(@Nullable ProgressIndicator indicator);
}
