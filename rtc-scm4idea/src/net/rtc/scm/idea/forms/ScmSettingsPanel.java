package net.rtc.scm.idea.forms;

import net.rtc.scm.idea.ScmApplicationSettings;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 13.04.14
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class ScmSettingsPanel {
  public static final String SCM_EXEC = "/usr/bin/scm";
  private JTextField pathToExecutable;
  private JPanel panel;
  private ScmApplicationSettings instance;

  public JPanel getPanel() {
    return panel;
  }


  public void init(ScmApplicationSettings instance) {
    //To change body of created methods use File | Settings | File Templates.
    this.instance = instance;
    pathToExecutable.setText(instance.getState().getPathToExecutable());
  }

  public void apply() {
    instance.getState().setPathToExecutable(pathToExecutable.getText());
  }

  public void reset() {
    instance.getState().setPathToExecutable(SCM_EXEC);
  }
}
