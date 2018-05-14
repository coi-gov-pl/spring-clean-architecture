package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2017-01-06
 */
@Configuration
public class ValidationConfiguration {

  @Bean
  CapitalizedValidator provideCapitalizedValidator() {
    return new CapitalizedValidatorImpl();
  }

  @Bean
  SpecifiedValidator provideSpecifiedValidator() {
    return new SpecifiedValidatorImpl();
  }
}
