package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
public interface StoringMappingContext extends MappingContext {
  void storeMappedInstance(Object source, Object target);
}
