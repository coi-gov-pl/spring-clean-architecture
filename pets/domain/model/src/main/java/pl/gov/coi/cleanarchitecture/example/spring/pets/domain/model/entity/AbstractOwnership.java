package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-07
 */

abstract class AbstractOwnership implements Serializable {

  @Getter
  private final Pet pet;
  @Getter
  private final Person person;
  private final long from;

  /**
   * Ownership base constructor
   *
   * @param pet    a pet
   * @param person a owner of the pet
   * @param from   a instant of time in which person became owner of this pet
   */
  AbstractOwnership(Pet pet, Person person, Instant from) {
    this.pet = pet;
    this.person = person;
    this.from = from.toEpochMilli();
  }

  public Instant getFrom() {
    return Instant.ofEpochMilli(from);
  }
}
