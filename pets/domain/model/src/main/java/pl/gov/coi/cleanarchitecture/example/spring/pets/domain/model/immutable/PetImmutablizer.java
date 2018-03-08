package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable.FormerOwnershipData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable.OwnershipData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable.PetData;
import pl.gov.coi.proxy.ProxyManager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
@RequiredArgsConstructor
public class PetImmutablizer implements Immutablizer<PetData, Pet> {
  private final Registry registry;

  @Override
  public Pet immutable(PetData mutable) {
    ProxyManager<Pet> petProxyManager = ProxyManager.create(Pet.class);
    Pet pet = petProxyManager.getProxy();
    registry.register(this, mutable, pet);

    Ownership ownership = immutablize(mutable.getOwnership());
    List<FormerOwnership> formerOwnerships = mutable.getFormerOwners()
      .stream()
      .map(this::immutablize)
      .collect(Collectors.toList());

    return new Pet(
      mutable.getName(),
      mutable.getRace(),
      ownership,
      formerOwnerships
    );
  }

  private Ownership immutablize(OwnershipData data) {
    OwnershipImmutablizer ownershipImmutablizer =
      new OwnershipImmutablizer(registry);
    return ownershipImmutablizer.immutable(data);
  }

  private FormerOwnership immutablize(FormerOwnershipData formerOwnershipData) {
    FormerOwnershipImmutablizer formerOwnershipImmutablizer =
      new FormerOwnershipImmutablizer(registry);
    return formerOwnershipImmutablizer.immutable(formerOwnershipData);
  }
}
