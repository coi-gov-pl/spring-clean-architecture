package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-14
 */
@RequiredArgsConstructor
final class PetContractMapperImpl implements PetContractMapper {
  private final EnumMapper<PetContract.Race, Race> mapper;

  @Override
  public Pet map(PetContract petContract,
                 Function<PetContract.Ownership, Person> personLoader) {
    PetContract.Race raceReq = petContract.getRace();
    Race race = mapper.map(raceReq);
    Pet pet = new Pet(
      petContract.getName(),
      race
    );
    Optional
      .ofNullable(petContract.getOwnership())
      .ifPresent(ownershipReq -> {
        Person person = personLoader.apply(ownershipReq);
        pet.setOwner(person);
      });
    return pet;
  }

  @Override
  public PetContract map(Pet pet) {
    throw new UnsupportedOperationException("not yet implemented");
  }
}
