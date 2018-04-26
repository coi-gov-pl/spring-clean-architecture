package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
@Service
@RequiredArgsConstructor
final class PetUpdateMappingProvider
  implements UpdateMappingProvider<Pet, PetData, MapperContext> {

  private final PetMapper petMapper;

  @Override
  public Mapping<Pet, PetData, MapperContext> provide() {
    return MapperContextMapping.mapperFor(
      Pet.class, PetData.class,
      petMapper::updateFromPet
    );
  }
}
