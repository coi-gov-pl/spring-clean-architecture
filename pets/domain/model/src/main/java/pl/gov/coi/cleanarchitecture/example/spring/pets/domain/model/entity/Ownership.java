package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-07
 */
public interface Ownership extends Serializable {
  Pet getPet();
  Person getPerson();
  Instant getFrom();

  void setPet(Pet pet);
  void setPerson(Person owner);
  void setFrom(Instant from);
}
