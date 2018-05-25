package pl.gov.coi.cleanarchitecture.example.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validator;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.12.16
 */
@Configuration
public class AppConfiguration {

  @Bean
  public Validator localValidatorFactoryBean(ConstraintValidatorFactory constraintValidatorFactory) {
    LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
    factoryBean.setConstraintValidatorFactory(constraintValidatorFactory);
    return factoryBean;
  }
}
