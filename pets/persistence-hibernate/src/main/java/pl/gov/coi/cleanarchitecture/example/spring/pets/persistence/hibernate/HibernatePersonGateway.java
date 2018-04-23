package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper.MapperFacade;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 23.04.18
 */
@RequiredArgsConstructor
final class HibernatePersonGateway implements PersonGateway {
  private final EntityManager entityManager;
  private final MapperFacade mapper;

  @Override
  public Optional<Person> findByNameAndSurname(String name, String surname) {
    TypedQuery<PersonData> query = entityManager
      .createQuery(
        "SELECT p " +
          "FROM PersonData p " +
          "WHERE p.name = :name AND p.surname = :surname",
        PersonData.class
      );
    query.setMaxResults(1);
    query.setParameter("name", name);
    query.setParameter("surname", surname);
    List<PersonData> people = query.getResultList();
    if (people.size() != 1) {
      return Optional.empty();
    }
    PersonData person = people.iterator().next();
    return Optional.of(mapper.map(person));
  }
}
