package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation;

import javax.validation.ConstraintValidator;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 14.05.18
 */
interface SpecifiedValidator extends ConstraintValidator<Specified, IsSpecified> {
}
