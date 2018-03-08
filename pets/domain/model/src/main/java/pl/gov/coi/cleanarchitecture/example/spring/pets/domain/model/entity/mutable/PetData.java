package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable;

import lombok.Data;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
@Data
public class PetData {
  private String name;
  private Race race;
  @Nullable
  private OwnershipData ownership;
  private List<FormerOwnershipData> formerOwners;

  public PetData() {
    // do nothing
  }
}
