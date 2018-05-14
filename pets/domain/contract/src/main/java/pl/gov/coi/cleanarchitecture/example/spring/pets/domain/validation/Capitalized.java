package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.12.16
 */
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CapitalizedValidator.class)
@Documented
@NotBlank
public @interface Capitalized {
  String message() default "{pl.gov.coi.cleanarchitecture.example.spring.pets" +
    ".domain.validation.Capitalized.message}";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
}
