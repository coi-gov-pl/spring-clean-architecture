package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Data
@NoArgsConstructor
public final class FormerOwnership implements Serializable {
  private Pet pet;
  private Person person;
  private Instant from;
  private Instant to;

  FormerOwnership(Ownership ownership) {
    this.pet = ownership.getPet();
    this.person = ownership.getPerson();
    this.from = ownership.getFrom();
    this.to = Instant.now();
  }

}
