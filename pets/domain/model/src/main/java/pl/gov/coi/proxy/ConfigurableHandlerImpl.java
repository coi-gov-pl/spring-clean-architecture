package pl.gov.coi.proxy;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
final class ConfigurableHandlerImpl<T> implements ConfigurableHandler<T> {
  @Nullable
  private T target;

  ConfigurableHandlerImpl() {
    // noting
  }

  @Override
  public void setTarget(T target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Objects.requireNonNull(target, "20180308:231529");
    return method.invoke(target, args);
  }
}
