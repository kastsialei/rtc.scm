package net.rtc.scm.idea.auth;

import com.intellij.openapi.project.Project;
import net.rtc.scm.idea.auth.AuthHandle;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 19.04.14
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public interface AuthHandler {
  AuthHandle createHandle(Project myProject);
}
