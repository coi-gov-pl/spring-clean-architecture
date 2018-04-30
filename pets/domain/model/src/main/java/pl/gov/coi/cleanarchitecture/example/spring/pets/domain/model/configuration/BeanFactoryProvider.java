package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.configuration;

import pl.wavesoftware.utils.stringify.configuration.BeanFactory;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-29
 */
public final class BeanFactoryProvider {

  @Nullable
  private static BeanFactory beanFactory;

  private BeanFactoryProvider() {
    // do nothing
  }

  public static Optional<BeanFactory> getBeanFactory() {
    return Optional.ofNullable(beanFactory);
  }

  static void setBeanFactory(BeanFactory beanFactory) {
    BeanFactoryProvider.beanFactory = beanFactory;
  }

}
