package net.rtc.scm.idea;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.VcsKey;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 04.04.14
 * Time: 22:49
 * To change this template use File | Settings | File Templates.
 */
public class ScmVcs extends AbstractVcs {


  private static final Logger LOG = Logger.getInstance(ScmVcs.class);
//  public static final String NAME = "rtcscm";

  public ScmVcs(Project project, String name) {
    super(project, name);
  }

  public ScmVcs(Project project, String name, VcsKey key) {
    super(project, name, key);
  }

  @Override
  public String getDisplayName() {
    return "rtcscm";
  }

  @Override
  public Configurable getConfigurable() {
    LOG.debug("getConfigurable");
    return new ScmConfigurable(getProject());
  }
}
