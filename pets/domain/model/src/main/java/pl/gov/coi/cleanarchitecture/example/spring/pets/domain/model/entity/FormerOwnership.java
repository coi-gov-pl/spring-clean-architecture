package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Data
@ToString(of = {"from", "to"})
@EqualsAndHashCode(callSuper = false)
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
