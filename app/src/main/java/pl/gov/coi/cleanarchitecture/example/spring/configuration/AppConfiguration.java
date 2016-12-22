package pl.gov.coi.cleanarchitecture.example.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.inject.Inject;
import javax.validation.Validator;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.12.16
 */
@Configuration
public class AppConfiguration {

  private final SpringAutowiredConstraintValidatorFactory constraintValidatorFactory;

  @Inject
  public AppConfiguration(SpringAutowiredConstraintValidatorFactory constraintValidatorFactory) {
    this.constraintValidatorFactory = constraintValidatorFactory;
  }

  @Bean
  public Validator localValidatorFactoryBean() {
    LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
    factoryBean.setConstraintValidatorFactory(constraintValidatorFactory);
    return factoryBean;
  }
}
