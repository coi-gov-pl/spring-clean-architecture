package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity;

import lombok.Getter;
import lombok.ToString;
import pl.gov.coi.example.spring.cleanarchitecture.core.domain.entity.Entity;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Getter
@ToString
public class Ownership implements Entity {
  @NotNull
  private final Pet pet;
  @NotNull
  private final Person person;
  @NotNull
  private final Instant from;

  public Ownership(Pet pet, Person person, Instant from) {
    this.pet = pet;
    this.person = person;
    this.from = from;

    person.addOwnership(this);
  }
}
