package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Getter
@Builder
@ToString
public class Ownership implements Entity {
  private final Pet pet;
  private final Person person;
  private final Instant from;

  public Ownership(Pet pet, Person person, Instant from) {
    this.pet = pet;
    this.person = person;
    this.from = from;

    person.addOwnership(this);
  }
}
