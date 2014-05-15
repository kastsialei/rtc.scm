package scm;

import scm.idea.AuthorizationConfigurable;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 15.05.14
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */
public class AuthorizationDecorator implements ScmCommand {
  /**
   * The user ID in the repository.
   */
  public static final String ARG_USERNAME = "--username";

  /**
   * The user password in the repository.
   */
  public static final String ARG_USERPWD = "--password";
  private final ScmCommand scmCommand;
  private final AuthorizationConfigurable authorizationConfigurable;

  public AuthorizationDecorator(ScmCommand scmCommand, AuthorizationConfigurable authorizationConfigurable) {
    this.scmCommand = scmCommand;
    this.authorizationConfigurable = authorizationConfigurable;
  }

  @Override
  public ScmCommandResult execute(CommandContext context) {
    scmCommand.addArg(new SimpleScmArg(ARG_USERNAME, authorizationConfigurable.getUsername()));
    scmCommand.addArg(new SimpleScmArg(ARG_USERPWD,  authorizationConfigurable.getUserPwd()));
    return scmCommand.execute(context);
  }

  @Override
  public void addArg(ScmArg arg) {
    scmCommand.addArg(arg);
  }
}
