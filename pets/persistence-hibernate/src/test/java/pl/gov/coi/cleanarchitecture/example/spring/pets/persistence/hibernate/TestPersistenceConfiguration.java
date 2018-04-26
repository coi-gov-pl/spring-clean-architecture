package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.ExampleDataPredicate;

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
}
