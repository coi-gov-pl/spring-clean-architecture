package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable.PetData;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
public final class PetMutablizer implements Mutablizer<Pet, PetData> {
  @Override
  public PetData mutable(Pet pet) {
    OwnershipMutablizer ownershipMutableizer = new OwnershipMutablizer(this);
    FormerOwnershipMutablizer formerOwnershipMutablizer =
      new FormerOwnershipMutablizer();
    PetData mutable = new PetData();
    mutable.setName(pet.getName());
    mutable.setRace(pet.getRace());
    pet.getOwnership()
      .ifPresent(o -> mutable.setOwnership(ownershipMutableizer.mutable(o)));
    mutable.setFormerOwners(
      StreamSupport.stream(pet.getFormerOwners().spliterator(), false)
      .map(formerOwnershipMutablizer::mutable)
      .collect(Collectors.toList())
    );

    return mutable;
  }

}
