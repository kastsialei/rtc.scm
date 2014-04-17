package net.rtc.scm.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.popup.BalloonPopupBuilderImpl;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 17.04.14
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public class ScmStart extends AnAction {
  public void actionPerformed(AnActionEvent e) {
    new BalloonPopupBuilderImpl(null, createComponent());
  }

  private JComponent createComponent() {
    return new JLabel("234567898765");

  }
}