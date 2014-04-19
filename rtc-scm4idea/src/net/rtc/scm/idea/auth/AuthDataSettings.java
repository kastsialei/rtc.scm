package net.rtc.scm.idea.auth;

import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.ide.passwordSafe.PasswordSafeException;
import com.intellij.ide.passwordSafe.impl.PasswordSafeImpl;
import com.intellij.ide.passwordSafe.impl.providers.masterKey.MasterKeyPasswordSafe;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 19.04.14
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public class AuthDataSettings implements PersistentStateComponent<AuthDataSettings.State> {
  private static final Logger log = Logger.getInstance(AuthDataSettings.class);

  public static final String SCM_PASSWORD_KEY = "rtc.scm.pwd.key";

  @Nullable
  @Override
  public State getState() {
    return null;
  }

  @Override
  public void loadState(State state) {
  }

  public class State {
    String repoUrl;
    String login;

    public String getRepoUrl() {
      return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
      this.repoUrl = repoUrl;
    }

    public String getLogin() {
      return login;
    }

    public void setLogin(String login) {
      this.login = login;
    }
    public String getPassword() {
      String password;
      final Project project = ProjectManager.getInstance().getDefaultProject();
      final PasswordSafeImpl passwordSafe = (PasswordSafeImpl) PasswordSafe.getInstance();
      try {
        password = passwordSafe.getMemoryProvider().getPassword(project, AuthDataSettings.class, SCM_PASSWORD_KEY);
        if (password != null) {
          return password;
        }
        final MasterKeyPasswordSafe masterKeyProvider = passwordSafe.getMasterKeyProvider();
        if (!masterKeyProvider.isEmpty()) {
          // workaround for: don't ask for master password, if the requested password is not there.
          // this should be fixed in PasswordSafe: don't ask master password to look for keys
          // until then we assume that is PasswordSafe was used (there is anything there), then it makes sense to look there.
          password = masterKeyProvider.getPassword(project, AuthDataSettings.class, SCM_PASSWORD_KEY);
        }
      }
      catch (PasswordSafeException e) {
        log.info("Couldn't get password for key [" + SCM_PASSWORD_KEY + "]", e);
//        masterPasswordRefused = true;
        password = "";
      }

//      passwordChanged = false;
      return password != null ? password : "";
    }

    public void setPassword(String password) {
//      passwordChanged = !getPassword().equals(password);
      try {
        PasswordSafe.getInstance().storePassword(null, AuthDataSettings.class, SCM_PASSWORD_KEY, password != null ? password : "");
      }
      catch (PasswordSafeException e) {
        log.info("Couldn't get password for key [" + SCM_PASSWORD_KEY + "]", e);
      }

    }
  }
}
