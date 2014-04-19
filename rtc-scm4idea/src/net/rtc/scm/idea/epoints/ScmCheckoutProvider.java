package net.rtc.scm.idea.epoints;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.CheckoutProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 16.04.14
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class ScmCheckoutProvider implements CheckoutProvider {
  @Override
  public void doCheckout(@NotNull Project project, @Nullable Listener listener) {
    System.out.println("doCheckout");

  }

  @Override
  public String getVcsName() {
    return "_rtcscm";
  }
}
