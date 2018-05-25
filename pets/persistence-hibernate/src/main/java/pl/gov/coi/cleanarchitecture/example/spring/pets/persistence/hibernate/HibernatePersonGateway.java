package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.FetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.OnGoingFetching;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper.MapperFacade;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.sql.QueryProvider;

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
  private final QueryProvider queryProvider;

  @Override
  public OnGoingFetching<Person> findByNameAndSurname(String name, String surname) {
    return new PersonOnGoingFetching(name, surname);
  }

  @RequiredArgsConstructor
  private final class PersonOnGoingFetching implements OnGoingFetching<Person> {
    private final String name;
    private final String surname;

    @Override
    public Optional<Person> fetch(FetchProfile<Person> profile) {
      TypedQuery<PersonData> query = getQuery(profile);
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

    private TypedQuery<PersonData> getQuery(FetchProfile<Person> profile) {
      String filename = "unknown";
      if (profile == PersonFetchProfile.WITH_OWNERSHIPS) {
        filename = "findByNameAndSurname-withOwnerships";
      } else if (profile == PersonFetchProfile.SOLE) {
        filename = "findByNameAndSurname-sole";
      }
      String jpql = queryProvider
        .forClass(this.getClass())
        .get(filename);
      return entityManager.createQuery(jpql, PersonData.class);
    }
  }
}
