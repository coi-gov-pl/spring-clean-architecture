package pl.gov.coi.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
public interface ConfigurableHandler<T> extends InvocationHandler {
  void setTarget(T target);
}
