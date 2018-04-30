package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.configuration.BeanFactorySetter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.ExampleDataPredicate;
import pl.wavesoftware.utils.stringify.configuration.BeanFactory;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 17.04.18
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("pl.gov.coi.cleanarchitecture.example.spring.pets")
@Import(PersistenceConfiguration.class)
class TestPersistenceConfiguration {

  @Bean
  ExampleDataPredicate provideDefault() {
    return () -> false;
  }

  @Bean
  @ConditionalOnMissingBean
  BeanFactory provideBeanFactory(ApplicationContext context,
                                 BeanFactorySetter beanFactorySetter) {
    BeanFactory beanFactory = context::getBean;
    beanFactorySetter.setBeanFactory(beanFactory);
    return beanFactory;
  }
}
