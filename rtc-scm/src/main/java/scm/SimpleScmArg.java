package scm;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 15.05.14
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class SimpleScmArg implements ScmArg {
  private final String argName;
  private final String argValue;

  public SimpleScmArg(String argName, String argValue) {
    this.argName = argName;
    this.argValue = argValue;
  }

  public String getArgName() {
    return argName;
  }

  public String getArgValue() {
    return argValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SimpleScmArg that = (SimpleScmArg) o;

    if (!argName.equals(that.argName)) return false;
    if (argValue != null ? !argValue.equals(that.argValue) : that.argValue != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = argName.hashCode();
    result = 31 * result + (argValue != null ? argValue.hashCode() : 0);
    return result;
  }
}
