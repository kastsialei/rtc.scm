package rtc.scm.executor;

import com.intellij.mock.MockProject;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 23.05.14
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
public class CommandLineUtilsTest {
  @Test(expected = IllegalArgumentException.class)
  public void forNullArgsWillThrowException() throws Exception {
    CommandLineUtils.prepareGeneralCommandLine(null, null);
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void forNullArgsWillThrowException() throws Exception {
//    CommandLineUtils.prepareGeneralCommandLine(null, null);
//  }
//  @Test(expected = IllegalArgumentException.class)
//  public void forNullArgsWillThrowException() throws Exception {
//    CommandLineUtils.prepareGeneralCommandLine(new MockProject(), null);
//  }

}
