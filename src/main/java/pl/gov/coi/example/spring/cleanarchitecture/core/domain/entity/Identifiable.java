package pl.gov.coi.example.spring.cleanarchitecture.core.domain.entity;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
public interface Identifiable<I> extends Entity {
  I getId();
}
