package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation;

import javax.validation.ConstraintValidatorContext;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 14.05.18
 */
public class SpecifiedValidatorImpl implements SpecifiedValidator {

  @Override
  public boolean isValid(IsSpecified value, ConstraintValidatorContext context) {
    return value.isSpecified();
  }
}
