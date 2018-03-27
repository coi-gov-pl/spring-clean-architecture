package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.impl;

import lombok.Data;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

import java.time.Instant;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-27
 */
@Data
final class FormerOwnershipImpl implements FormerOwnership {
  private Pet pet;
  private Person person;
  private Instant from;
  private Instant to;

  FormerOwnershipImpl(Ownership ownership) {
    this.pet = ownership.getPet();
    this.person = ownership.getPerson();
    this.from = ownership.getFrom();
    this.to = Instant.now();
  }

}
