package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.configuration;

import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;
import pl.wavesoftware.eid.exceptions.EidIndexOutOfBoundsException;
import pl.wavesoftware.utils.stringify.configuration.BeanFactory;
import pl.wavesoftware.utils.stringify.configuration.InspectionPoint;
import pl.wavesoftware.utils.stringify.lang.Predicate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-29
 */
public final class BeanFactoryProvider {

  private static BeanFactory beanFactory = new StubBeanFactory();

  private BeanFactoryProvider() {
    // do nothing
  }

  public static BeanFactory getBeanFactory() {
    return beanFactory;
  }

  static void setBeanFactory(BeanFactory beanFactory) {
    BeanFactoryProvider.beanFactory = beanFactory;
  }

  private static final class StubBeanFactory implements BeanFactory {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> contractClass) {
      if (Predicate.class.isAssignableFrom(contractClass) && contractClass.isInterface()) {
        Class<? extends Predicate<InspectionPoint>> cls =
          (Class<? extends Predicate<InspectionPoint>>) contractClass;
        Predicate<InspectionPoint> predicate = createPredicateFalseProxy(cls);
        return (T) predicate;
      }
      throw new EidIndexOutOfBoundsException(
        new Eid("20180515:175613"),
        "Only interfaces extending Predicate can be instantiated by default, " +
          "provide your own implementation of BeanFactory"
      );
    }

    private <T extends Predicate<InspectionPoint>> T createPredicateFalseProxy(
      Class<T> contractClass) {

      InvocationHandler handler = (proxy, method, args) -> false;
      @SuppressWarnings("unchecked")
      Class<T> proxyClass = (Class<T>) java.lang.reflect.Proxy.getProxyClass(
        Thread.currentThread().getContextClassLoader(),
        contractClass
      );
      try {
        return proxyClass
          .getConstructor(InvocationHandler.class)
          .newInstance(handler);
      } catch (InstantiationException |
        IllegalAccessException |
        InvocationTargetException |
        NoSuchMethodException e) {
        throw new EidIllegalStateException("20180515:180325", e);
      }
    }
  }
}
