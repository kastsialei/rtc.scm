package net.rtc.scm.idea;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import net.rtc.scm.idea.forms.ScmSettingsPanel;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 04.04.14
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public class ScmConfigurable implements Configurable {


  private Project project;
  private ScmSettingsPanel panel;

  public ScmConfigurable(Project project) {
    //To change body of created methods use File | Settings | File Templates.
    this.project = project;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return "rtcscm";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    panel = new ScmSettingsPanel();
    panel.init(ScmApplicationSettings.getInstance());
    return panel.getPanel();  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean isModified() {
    return true;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void apply() throws ConfigurationException {
    panel.apply();
  }

  @Override
  public void reset() {
    panel.reset();
  }

  @Override
  public void disposeUIResources() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
