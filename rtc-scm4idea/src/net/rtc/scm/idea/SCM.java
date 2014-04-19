package net.rtc.scm.idea;

import net.rtc.scm.idea.auth.AuthHandler;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 19.04.14
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public interface SCM {
  public void login(AuthHandler authHandler);
  public void logout();
  public void showHistory();
}
