package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Component
@RequiredArgsConstructor
final class PetToPetDataMapperImpl implements PetToPetDataMapper {

  private final OwnershipToDataMapper ownershipMapper;

  @Override
  public Pet map(PetData petData) {
    return null;
  }

  @Override
  public PetData map(Pet pet) {
    PetData data = new PetData();
    data.setName(pet.getName());
    data.setRace(pet.getRace());
    pet.getOwnership()
      .map(ownershipMapper::map)
      .ifPresent(data::setOwnership);
    return data;
  }
}
