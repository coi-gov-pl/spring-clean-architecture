package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Getter
@Setter
@NoArgsConstructor
public final class FormerOwnership extends AbstractEntity<FormerOwnership>
  implements Serializable {

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
