package net.rtc.scm.idea.epoints;

import com.intellij.openapi.diagnostic.Log;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.ChangesViewRefresher;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 19.04.14
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class ScmRefreshChangesViewRefresher implements ChangesViewRefresher {
  private static final Logger LOG = Logger.getInstance(ScmRefreshChangesViewRefresher.class);

  @Override
  public void refresh(Project project) {
    LOG.debug("refresh");

  }
}
