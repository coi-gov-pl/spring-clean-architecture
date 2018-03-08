package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
@Data
public class PersonData {
  private String name;
  private String surname;
  private List<OwnershipData> ownerships;

  public PersonData() {
    // do noting
  }
}
