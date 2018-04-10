package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-07
 */
public interface Ownership extends Serializable {
  /**
   * Pet getter
   * @return a pet
   */
  Pet getPet();

  /**
   * Owner getter
   * @return an owner
   */
  Person getPerson();

  /**
   * Return instant from which ownership is active
   * @return an from instant time
   */
  Instant getFrom();

  /**
   * Setter for Pet
   * @param pet a pet
   */
  void setPet(Pet pet);

  /**
   * Setter for owner person
   * @param owner an owner
   */
  void setPerson(Person owner);

  /**
   * Sets ownership start time
   * @param from a time to start
   */
  void setFrom(Instant from);
}
