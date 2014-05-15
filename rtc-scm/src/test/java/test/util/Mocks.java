package test.util;

import static org.mockito.Mockito.mock;

/**
 * Created with IntelliJ IDEA.
 * User: gosha
 * Date: 15.05.14
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public final class Mocks {
  public static <T>  T anyObjectOfType(Class<T> clazz) {
    return mock(clazz);
  }
}
