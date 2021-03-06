package net.rtc.scm.idea;

import com.intellij.lifecycle.PeriodicalTasksCloser;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 13.04.14
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
@State(name = "rtcscm.settings", storages = {@Storage(file = StoragePathMacros.WORKSPACE_FILE)})
public class ScmProjectSettings implements PersistentStateComponent<ScmProjectSettings.State> {
  private final ScmApplicationSettings myAppSettings;
  private State myState = new State();

  public ScmProjectSettings(ScmApplicationSettings myAppSettings) {
    this.myAppSettings = myAppSettings;
  }
  public static ScmProjectSettings getInstance(Project project) {
    return PeriodicalTasksCloser.getInstance().safeGetService(project, ScmProjectSettings.class);
  }
  @Nullable
  @Override
  public State getState() {
    return myState;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void loadState(State state) {
    myState = state;
  }

  public class State {
    String repoUrl;
    Set<String> components;
    String login;

    public String getRepoUrl() {
      return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
      this.repoUrl = repoUrl;
    }

    public Set<String> getComponents() {
      return components;
    }

    public void setComponents(Set<String> components) {
      this.components = components;
    }

    public String getLogin() {
      return login;
    }

    public void setLogin(String login) {
      this.login = login;
    }
  }
}
