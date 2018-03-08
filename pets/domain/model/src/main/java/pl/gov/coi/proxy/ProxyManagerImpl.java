package pl.gov.coi.proxy;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
final class ProxyManagerImpl<T> implements ProxyManager<T> {
  private final ConfigurableHandler<T> handler;
  private final T proxy;

  ProxyManagerImpl(Class<T> cls) {
    this.handler = new ConfigurableHandlerImpl<>();
    this.proxy = createProxy(cls, handler);
  }

  private static <T> T createProxy(Class<T> cls, ConfigurableHandler<T> handler) {
    return cls.cast(
      java.lang.reflect.Proxy.newProxyInstance(
        cls.getClassLoader(),
        new Class[] { cls },
        handler)
    );
  }

  @Override
  public T getProxy() {
    return proxy;
  }

  @Override
  public void bind(T target) {
    handler.setTarget(target);
  }
}
