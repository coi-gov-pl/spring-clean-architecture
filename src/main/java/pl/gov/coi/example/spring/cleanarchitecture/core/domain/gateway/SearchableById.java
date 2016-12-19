package pl.gov.coi.example.spring.cleanarchitecture.core.domain.gateway;

import pl.gov.coi.example.spring.cleanarchitecture.core.domain.entity.Identifiable;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface SearchableById<I, E extends Identifiable<I>> {
  Optional<E> searchById(I id);
  E getById(I id);
}
