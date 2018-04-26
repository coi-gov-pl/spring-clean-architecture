package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
@Service
@RequiredArgsConstructor
final class PersonUpdateMappingProvider implements
  UpdateMappingProvider<Person, PersonData, MapperContext> {
  private final PersonMapper personMapper;

  @Override
  public Mapping<Person, PersonData, MapperContext> provide() {
    return MapperContextMapping.mapperFor(
      Person.class, PersonData.class,
      personMapper::updateFromPerson
    );
  }
}
