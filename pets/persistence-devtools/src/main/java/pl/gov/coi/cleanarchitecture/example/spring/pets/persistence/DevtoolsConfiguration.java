package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
@Configuration
class DevtoolsConfiguration {
  @Bean
  @ConditionalOnMissingBean
  ExampleDataPredicate provideDefault() {
    return () -> true;
  }
}
