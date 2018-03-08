package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable.FormerOwnershipData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-09
 */
@RequiredArgsConstructor
public class FormerOwnershipImmutablizer implements
  Immutablizer<FormerOwnershipData, FormerOwnership> {
  private final Registry registry;

  @Override
  public FormerOwnership immutable(FormerOwnershipData mutable) {
    return null;
  }
}
