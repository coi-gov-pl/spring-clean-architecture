package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
interface MappingContext {
  <T> Optional<T> getMappedInstance(Object source, Class<T> targetType);
}
