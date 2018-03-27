package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.impl;

import lombok.Data;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

import java.time.Instant;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-27
 */
@Data
final class OwnershipImpl implements Ownership {
  private Pet pet;
  private Person person;
  private Instant from;

  /**
   * Default constructor
   */
  OwnershipImpl() {
    from = Instant.now();
  }
}
