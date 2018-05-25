package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wavesoftware.utils.stringify.configuration.BeanFactory;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-30
 */
@Configuration
public class DomainModelConfiguration {

  @Bean
  public BeanFactory provideBeanFactory(ApplicationContext context) {
    BeanFactory factory = context::getBean;
    BeanFactoryProvider.setBeanFactory(factory);
    return factory;
  }

}
