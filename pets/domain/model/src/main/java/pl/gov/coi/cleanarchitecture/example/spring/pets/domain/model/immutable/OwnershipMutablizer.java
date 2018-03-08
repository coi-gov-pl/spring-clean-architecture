package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable.OwnershipData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
@RequiredArgsConstructor
final class OwnershipMutablizer implements Mutablizer<Ownership, OwnershipData> {
  private final PetMutablizer petMutableizer;

  @Override
  public OwnershipData mutable(Ownership immutable) {
    PersonMutablizer personMutablizer = new PersonMutablizer();
    PetMutablizer petMutablizer = new PetMutablizer();
    OwnershipData data = new OwnershipData();
    data.setFrom(immutable.getFrom());
    data.setPerson(personMutablizer.mutable(immutable.getPerson()));
    data.setPet(petMutablizer.mutable(immutable.getPet()));
    return data;
  }
}
