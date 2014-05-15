package scm;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 15.05.14
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseScmCommand implements ScmCommand {
  private Set<ScmArg> argSet;

  protected BaseScmCommand() {
    this.argSet = new LinkedHashSet<ScmArg>();
  }

  @Override
  public final void addArg(ScmArg arg) {
    argSet.add(arg);
  }
}
