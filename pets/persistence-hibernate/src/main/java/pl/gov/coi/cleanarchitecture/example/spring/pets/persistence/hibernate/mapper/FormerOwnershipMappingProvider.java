package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.FormerOwnershipData;
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
final class FormerOwnershipMappingProvider implements
  MappingProvider<FormerOwnership, FormerOwnershipData, CompositeContext> {

  private final FormerOwnershipMapper formerOwnershipMapper;

  @Override
  public Mapping<FormerOwnership, FormerOwnershipData, CompositeContext> provide() {
    return AbstractCompositeContextMapping.mappingFor(
      FormerOwnership.class, FormerOwnershipData.class,
      formerOwnershipMapper::updateFromFormerOwnership
    );
  }
}
