package net.rtc.scm.idea;

import com.intellij.openapi.components.*;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 12.04.14
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
@State(
    name = "RTCSCM.Application.Settings",
    storages = {@Storage(file = StoragePathMacros.APP_CONFIG + "/vcs.xml")})
public class ScmApplicationSettings implements PersistentStateComponent<ScmApplicationSettings.Settings> {
  private Settings settings = new Settings();

  @Nullable
  @Override
  public Settings getState() {
    return settings;
  }

  @Override
  public void loadState(Settings state) {
    this.settings = state;
  }

  public static ScmApplicationSettings getInstance() {
    return ServiceManager.getService(ScmApplicationSettings.class);
  }

  public static class Settings {
    private String pathToExecutable;
    private boolean storePassword;

    public String getPathToExecutable() {
      return pathToExecutable;
    }

    public void setPathToExecutable(String pathToExecutable) {
      this.pathToExecutable = pathToExecutable;
    }

    public boolean isStorePassword() {
      return storePassword;
    }

    public void setStorePassword(boolean storePassword) {
      this.storePassword = storePassword;
    }
  }
}
