package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData;
import pl.wavesoftware.utils.mapstruct.jpa.AbstractCompositeContextMapping;
import pl.wavesoftware.utils.mapstruct.jpa.CompositeContext;
import pl.wavesoftware.utils.mapstruct.jpa.Mapping;
import pl.wavesoftware.utils.mapstruct.jpa.MappingProvider;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
@Service
@RequiredArgsConstructor
final class OwnershipMappingProvider implements
  MappingProvider<Ownership, OwnershipData, CompositeContext> {

  private final OwnershipMapper ownershipMapper;

  @Override
  public Mapping<Ownership, OwnershipData, CompositeContext> provide() {
    return AbstractCompositeContextMapping.mappingFor(
      Ownership.class, OwnershipData.class,
      ownershipMapper::updateFromOwnership
    );
  }
}
