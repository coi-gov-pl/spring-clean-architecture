package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable.OwnershipData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-09
 */
@RequiredArgsConstructor
public class OwnershipImmutablizer implements Immutablizer<OwnershipData, Ownership> {
  private final Registry registry;

  @Override
  public Ownership immutable(OwnershipData mutable) {
    return null;
  }
}
