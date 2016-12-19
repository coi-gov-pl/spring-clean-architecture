package pl.gov.coi.example.spring.cleanarchitecture.core.domain.gateway;

import pl.gov.coi.example.spring.cleanarchitecture.core.domain.entity.Entity;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public interface Removable<E extends Entity> {
  E remove(E entity);
}
