package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable;

import lombok.Data;

import java.time.Instant;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-08
 */
@Data
public class OwnershipData {
  private PetData pet;
  private PersonData person;
  private Instant from;

  public OwnershipData() {
    // do nothing
  }
}
