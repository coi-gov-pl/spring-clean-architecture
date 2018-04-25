package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 25.04.18
 */
public interface JpaMappingContextFactory {
  JpaMappingContext produce(StoringMappingContext parentContext,
                            Mappings mappings);
}
