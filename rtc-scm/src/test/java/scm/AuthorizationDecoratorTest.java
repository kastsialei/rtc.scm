package scm;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import scm.idea.AuthorizationConfigurable;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static test.util.Mocks.anyObjectOfType;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 15.05.14
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */
public class AuthorizationDecoratorTest {

  public static final String USERNAME = "username";
  private static final String USERPWD = "userPwd";

  @Test
  public void shoulAddRequiredArgsToDecorate() throws Exception {
    ScmCommand decorate = mock(ScmCommand.class);
    AuthorizationConfigurable authorizationConfigurable = mock(AuthorizationConfigurable.class);
    when(authorizationConfigurable.getUsername()).thenReturn(USERNAME);
    when(authorizationConfigurable.getUserPwd()).thenReturn(USERPWD);
    ScmCommand decorator = new AuthorizationDecorator(decorate, authorizationConfigurable);
    decorator.execute(anyObjectOfType(CommandContext.class));
    verify(decorate).addArg(eq(new SimpleScmArg(AuthorizationDecorator.ARG_USERNAME, USERNAME)));
    verify(decorate).addArg(eq(new SimpleScmArg(AuthorizationDecorator.ARG_USERPWD, USERPWD)));
    verify(decorate).execute(any(CommandContext.class));
  }

  @Test
  public void shouldAddArgToDecorateCommand() throws Exception {
    ScmCommand decorate = mock(ScmCommand.class);
    AuthorizationConfigurable authorizationConfigurable = mock(AuthorizationConfigurable.class);
    ScmCommand decorator = (ScmCommand) new AuthorizationDecorator(decorate, mock(AuthorizationConfigurable.class));
    ScmArg arg = anyObjectOfType(ScmArg.class);
    decorator.addArg(arg);

    verify(decorate).addArg(arg);

  }
}
