package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 25.04.18
 */
@Service
@RequiredArgsConstructor
final class JpaMappingContextFactoryImpl implements JpaMappingContextFactory {
  private final EntityManager entityManager;

  @Override
  public JpaMappingContext produce(StoringMappingContext parentContext,
                                   Mappings mappings) {
    return new JpaMappingContextImpl(entityManager, parentContext, mappings);
  }
}
