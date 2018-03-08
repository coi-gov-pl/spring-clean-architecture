package pl.gov.coi.proxy;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 * @param <T> a class type
 */
public interface ProxyManager<T> {

  /**
   * Gets proxy object
   * @return a proxy
   */
  T getProxy();

  /**
   * Binds proxy to implementation
   * @param target a target implementation
   */
  void bind(T target);

  /**
   * Creates a proxy manager object
   * @param cls a class to create proxy
   * @param <T> a class type
   * @return a proxy manager object
   */
  static <T> ProxyManager<T> create(Class<T> cls) {
    return new ProxyManagerImpl<>(cls);
  }
}
