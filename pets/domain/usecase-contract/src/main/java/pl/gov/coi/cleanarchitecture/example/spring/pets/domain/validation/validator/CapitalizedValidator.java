package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.validator;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.constraint.Capitalized;

import javax.validation.ConstraintValidator;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.12.16
 */
public interface CapitalizedValidator extends ConstraintValidator<Capitalized, String> {
}
