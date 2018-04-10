package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@RequiredArgsConstructor
final class PetToPetDataConverterImpl implements PetToPetDataConverter {

  private final UnitOfWork unitOfWork;
  private final OwnershipToDataConverter ownershipToDataConverter;

  @Override
  public PetData convert(Pet pet) {
    WorkContainer<Pet, PetData> workContainer = unitOfWork.containerOf(Pet.class);
    if (workContainer.isContained(pet)) {
      return workContainer.get(pet);
    }
    PetData data = new PetData();
    workContainer.put(pet, data);
    data.setName(pet.getName());
    data.setRace(pet.getRace());
    pet.getOwnership()
      .map(ownershipToDataConverter::convert)
      .ifPresent(data::setOwnership);
    return data;
  }

  @Override
  public void close() {
    unitOfWork.close();
  }
}
