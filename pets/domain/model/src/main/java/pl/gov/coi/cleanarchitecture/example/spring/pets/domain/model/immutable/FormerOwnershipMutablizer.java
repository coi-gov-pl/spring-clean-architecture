package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable.FormerOwnershipData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
public class FormerOwnershipMutablizer implements Mutablizer<FormerOwnership, FormerOwnershipData> {
  @Override
  public FormerOwnershipData mutable(FormerOwnership immutable) {
    return null;
  }
}
