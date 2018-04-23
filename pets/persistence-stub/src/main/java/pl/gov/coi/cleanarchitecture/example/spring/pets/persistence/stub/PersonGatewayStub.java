package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
@RequiredArgsConstructor
final class PersonGatewayStub implements PersonGateway {
  private final StubDatabase database;

  @Override
  public Optional<Person> findByNameAndSurname(String name, String surname) {

    for (Pet pet : database.getPets()) {
      Finder finder = new Finder(pet, name, surname);
      Optional<Person> opt = finder.findFromOwnership()
        .map(Optional::of)
        .orElseGet(finder::findFromFormerOwnership);
      if (opt.isPresent()) {
        return opt;
      }
    }
    return Optional.empty();
  }

  @RequiredArgsConstructor
  private static final class Finder {
    private final Pet pet;
    private final String name;
    private final String surname;

    private Optional<Person> findFromOwnership() {
      Optional<Ownership> optOwnership = pet.getOwnership();
      if (optOwnership.isPresent()) {
        Person person = optOwnership.get().getPerson();
        if (person.getName().equals(name) && person.getSurname().equals(surname)) {
          return Optional.of(person);
        }
      }
      return Optional.empty();
    }

    private Optional<Person> findFromFormerOwnership() {
      for (FormerOwnership formerOwnership : pet.getFormerOwners()) {
        Person person = formerOwnership.getPerson();
        if (person.getName().equals(name) && person.getSurname().equals(surname)) {
          return Optional.of(person);
        }
      }
      return Optional.empty();
    }

  }


}
