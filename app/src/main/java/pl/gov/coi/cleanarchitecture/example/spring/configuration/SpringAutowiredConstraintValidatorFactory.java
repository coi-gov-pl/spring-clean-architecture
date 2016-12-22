package pl.gov.coi.cleanarchitecture.example.spring.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.12.16
 */
@Service
class SpringAutowiredConstraintValidatorFactory implements ConstraintValidatorFactory {

  private static final Logger DEFAULT_LOGGER
    = LoggerFactory.getLogger(SpringAutowiredConstraintValidatorFactory.class);

  private final Logger logger;
  private final AutowireCapableBeanFactory beanFactory;

  @Inject
  public SpringAutowiredConstraintValidatorFactory(AutowireCapableBeanFactory beanFactory) {
    this(beanFactory, DEFAULT_LOGGER);
  }

  SpringAutowiredConstraintValidatorFactory(AutowireCapableBeanFactory beanFactory,
                                            Logger logger) {
    this.beanFactory = beanFactory;
    this.logger = logger;
  }

  @Override
  public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
    T bean = createBasicValidator(key);

    if (bean == null) {
      bean = findValidatorImplementation(key);
    }

    if (bean == null) {
      logger.warn("Failed to get validator of class " + key.getSimpleName());
    }

    return bean;
  }

  private <T extends ConstraintValidator<?, ?>> T findValidatorImplementation(Class<T> key) {
    T bean = null;
    try {
      trace("Trying to find a validator bean of class {}", key.getSimpleName());
      bean = this.beanFactory.getBean(key);
    } catch (BeansException exc) {
      trace("Failed to find a bean of class {}", key.getSimpleName());
      trace(exc);
    }
    return bean;
  }

  private <T extends ConstraintValidator<?, ?>> T createBasicValidator(Class<T> key) {
    T bean = null;
    try {
      trace("Creating a new validator bean of class {}", key.getSimpleName());
      bean = this.beanFactory.createBean(key);
    } catch (BeansException exc) {
      trace("Failed to create a validator of class {}", key.getSimpleName());
      trace(exc);
    }
    return bean;
  }

  private void trace(Exception exc) {
    if (logger.isTraceEnabled()) {
      logger.trace(exc.getMessage(), exc);
    }
  }

  private void trace(String message, Object... args) {
    if (logger.isTraceEnabled()) {
      logger.trace(message, args);
    }
  }

  @Override
  public void releaseInstance(ConstraintValidator<?, ?> instance) {
    trace(
      "Destroying validator bean of class {}",
      instance.getClass().getSimpleName()
    );
    beanFactory.destroyBean(instance);
  }

}
