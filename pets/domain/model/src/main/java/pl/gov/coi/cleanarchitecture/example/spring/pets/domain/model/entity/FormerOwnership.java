package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@ToString
@Getter
public class FormerOwnership extends Ownership {
  private final Instant to;

  public FormerOwnership(Pet pet, Person person, Instant from, Instant to) {
    super(pet, person, from);
    this.to = to;
  }
}
