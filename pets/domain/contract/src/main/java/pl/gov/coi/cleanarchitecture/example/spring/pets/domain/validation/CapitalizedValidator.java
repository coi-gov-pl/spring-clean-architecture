package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation;

import javax.validation.ConstraintValidator;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.12.16
 */
interface CapitalizedValidator extends ConstraintValidator<Capitalized, String> {
}
