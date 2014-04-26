package scm;

import idea.CommandHandler;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 26.04.14
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */
public interface ScmCommand {
  void execute(CommandHandler handler);

}
