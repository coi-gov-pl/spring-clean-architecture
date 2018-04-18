package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines if given field in object should be inspected
 *
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 18.04.18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Inspect {
  /**
   * Should show also fields with NULL values.
   */
  boolean showNull() default false;
}
