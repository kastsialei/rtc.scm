package rtc.scm.executor;

import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 20.05.14
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */
public class ScmConsole {

  private final ConsoleView consoleView;

  public ScmConsole(Project project) {
    consoleView = new ConsoleViewImpl(project, true);
    }

    public boolean canPause() {
      return false;
    }

    public boolean isOutputPaused() {
      return false;
    }

    public void setOutputPaused(boolean outputPaused) {
    }

    public void attachToProcess(ProcessHandler processHandler) {
      processHandler.addProcessListener(new ProcessAdapter() {
        @Override
        public void onTextAvailable(ProcessEvent event, Key outputType) {
          System.out.print(event.getText());
        }

        @Override
        public void processTerminated(ProcessEvent event) {
          System.out.println("PROCESS TERMINATED: " + event.getExitCode());
        }
      });
    }

    protected void doPrint(String text) {
      System.out.print(text);
    }
    public void systemMessage(String message){
      doPrint(message);
    }
}
