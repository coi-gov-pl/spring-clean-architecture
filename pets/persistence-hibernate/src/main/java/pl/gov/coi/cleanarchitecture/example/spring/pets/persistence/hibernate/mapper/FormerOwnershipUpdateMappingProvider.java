package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.FormerOwnershipData;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
@Service
@RequiredArgsConstructor
final class FormerOwnershipUpdateMappingProvider implements
  UpdateMappingProvider<FormerOwnership, FormerOwnershipData, MapperContext> {

  private final FormerOwnershipMapper formerOwnershipMapper;

  @Override
  public Mapping<FormerOwnership, FormerOwnershipData, MapperContext> provide() {
    return MapperContextMapping.mapperFor(
      FormerOwnership.class, FormerOwnershipData.class,
      formerOwnershipMapper::updateFromFormerOwnership
    );
  }
}
