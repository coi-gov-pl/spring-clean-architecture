package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.interactor;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.wavesoftware.eid.exceptions.Eid;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@RequiredArgsConstructor
final class LoadPetInteractorImpl implements LoadPetInteractor {
  private final PetsGateway petsGateway;
  private final EnumMapper<PetContract.Race, Race> raceEnumMapper;

  @Override
  public Optional<PetContract> findPetByReference(EntityReference reference) {
    if (reference.getType() != Pet.class) {
      throw new SecurityException(
        new EidIllegalStateException(new Eid("20180511:212223"))
      );
    }

    Optional<Pet> pet = petsGateway
      .findByReference(reference::getReference)
      .fetch(PetFetchProfile.WITH_OWNER);
    return pet.map(this::petAsContract);
  }

  private PetContract petAsContract(Pet pet) {
    PetContract.PetContractBuilder builder = PetContract.builder();
    builder.name(pet.getName())
      .race(mapRace(pet.getRace()));
    pet.getOwnership()
      .ifPresent(ownership ->
        builder.ownership(new PetContract.Ownership(
          ownership.getPerson().getName(),
          ownership.getPerson().getSurname()
        ))
      );
    return builder.build();
  }

  private PetContract.Race mapRace(Race race) {
    return raceEnumMapper.reverseMap(race);
  }
}
