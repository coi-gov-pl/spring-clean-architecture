package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.configuration;

import org.springframework.stereotype.Service;
import pl.wavesoftware.utils.stringify.configuration.BeanFactory;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-30
 */
@Service
public final class BeanFactorySetter {
  public void setBeanFactory(BeanFactory beanFactory) {
    BeanFactoryProvider.setBeanFactory(beanFactory);
  }
}
