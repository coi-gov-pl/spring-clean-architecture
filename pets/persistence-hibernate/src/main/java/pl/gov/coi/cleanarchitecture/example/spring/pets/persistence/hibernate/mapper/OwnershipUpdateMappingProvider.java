package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
@Service
@RequiredArgsConstructor
final class OwnershipUpdateMappingProvider implements
  UpdateMappingProvider<Ownership, OwnershipData, MapperContext> {

  private final OwnershipMapper ownershipMapper;

  @Override
  public Mapping<Ownership, OwnershipData, MapperContext> provide() {
    return MapperContextMapping.mapperFor(
      Ownership.class, OwnershipData.class,
      ownershipMapper::updateFromOwnership
    );
  }
}
