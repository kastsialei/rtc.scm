package rtc.scm.executor;
import com.intellij.execution.filters.*;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.MessageView;
import org.jetbrains.annotations.Nullable;


import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScmConsole {
  private static final Key<rtc.scm.executor.ScmConsole> CONSOLE_KEY = Key.create("SCM_CONSOLE_KEY");

  private static final String CONSOLE_FILTER_REGEXP =
      "(?:^|(?:\\[\\w+\\]\\s*))" + RegexpFilter.FILE_PATH_MACROS + ":\\[" + RegexpFilter.LINE_MACROS + "," + RegexpFilter.COLUMN_MACROS + "]";

  private final String myTitle;
  private final Project myProject;
  private final ConsoleView myConsoleView;
  private final AtomicBoolean isOpen = new AtomicBoolean(false);
  private boolean isFinished;


  public ScmConsole(String title,
                    Project project) {
    myTitle = title;
    myProject = project;
    myConsoleView = createConsoleView();
  }


  private ConsoleView createConsoleView() {
    return createConsoleBuilder(myProject).getConsole();
  }

  public static TextConsoleBuilder createConsoleBuilder(final Project project) {
    TextConsoleBuilder builder = TextConsoleBuilderFactory.getInstance().createBuilder(project);

    final List<Filter> filters = ExceptionFilters.getFilters(GlobalSearchScope.allScope(project));
    for (Filter filter : filters) {
      builder.addFilter(filter);
    }
    builder.addFilter(new RegexpFilter(project, CONSOLE_FILTER_REGEXP) {
      @Nullable
      @Override
      protected HyperlinkInfo createOpenFileHyperlink(String fileName, int line, int column) {
        HyperlinkInfo res = super.createOpenFileHyperlink(fileName, line, column);
        if (res == null && fileName.startsWith("\\") && SystemInfo.isWindows) {
          // Maven cut prefix 'C:\' from paths on Windows
          VirtualFile[] roots = ProjectRootManager.getInstance(project).getContentRoots();
          if (roots.length > 0) {
            String projectPath = roots[0].getPath();
            if (projectPath.matches("[A-Z]:[\\\\/].+")) {
              res = super.createOpenFileHyperlink(projectPath.charAt(0) + ":" + fileName, line, column);
            }
          }

        }

        return res;
      }
    });

    return builder;
  }

  public boolean canPause() {
    return myConsoleView.canPause();
  }

  public boolean isOutputPaused() {
    return myConsoleView.isOutputPaused();
  }

  public void setOutputPaused(boolean outputPaused) {
    myConsoleView.setOutputPaused(outputPaused);
  }

  public void attachToProcess(ProcessHandler processHandler) {
    myConsoleView.attachToProcess(processHandler);
    processHandler.addProcessListener(new ProcessAdapter() {
      @Override
      public void onTextAvailable(ProcessEvent event, Key outputType) {
        ensureAttachedToToolWindow();
      }
    });
  }

  protected void doPrint(String text) {
    ensureAttachedToToolWindow();

    ConsoleViewContentType contentType = ConsoleViewContentType.NORMAL_OUTPUT;
    myConsoleView.print(text, contentType);
  }

  private void ensureAttachedToToolWindow() {
    if (!isOpen.compareAndSet(false, true)) return;
    ApplicationManager.getApplication().invokeLater(new Runnable() {
      public void run() {
        if (myProject.isDisposed()) return;
        MessageView messageView = MessageView.SERVICE.getInstance(myProject);

        Content content = ContentFactory.SERVICE.getInstance().createContent(
            myConsoleView.getComponent(), myTitle, true);
        content.putUserData(CONSOLE_KEY, ScmConsole.this);
        messageView.getContentManager().addContent(content);
        messageView.getContentManager().setSelectedContent(content);

        // remove unused tabs
        for (Content each : messageView.getContentManager().getContents()) {
          if (each.isPinned()) continue;
          if (each == content) continue;

          ScmConsole console = each.getUserData(CONSOLE_KEY);
          if (console == null) continue;

          if (!myTitle.equals(console.myTitle)) continue;

          if (console.isFinished()) {
            messageView.getContentManager().removeContent(each, false);
          }
        }

        ToolWindow toolWindow = ToolWindowManager.getInstance(myProject).getToolWindow(ToolWindowId.MESSAGES_WINDOW);
        if (!toolWindow.isActive()) {
          toolWindow.activate(null, false);
        }
      }
    });
  }

  public void close() {
    MessageView messageView = MessageView.SERVICE.getInstance(myProject);
    for (Content each : messageView.getContentManager().getContents()) {
      ScmConsole console = each.getUserData(CONSOLE_KEY);
      if (console != null) {
        messageView.getContentManager().removeContent(each, true);
        return;
      }
    }
  }
  public boolean isFinished() {
    return isFinished;
  }

  public void finish() {
    isFinished = true;
  }
  public void systemMessage(String string) {
    doPrint(string);
//    printMessage(level, string, throwable);
  }

}
