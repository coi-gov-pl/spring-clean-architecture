package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;
import pl.wavesoftware.utils.mapstruct.jpa.AbstractCompositeContextMapping;
import pl.wavesoftware.utils.mapstruct.jpa.CompositeContext;
import pl.wavesoftware.utils.mapstruct.jpa.Mapping;
import pl.wavesoftware.utils.mapstruct.jpa.MappingProvider;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
@Service
@RequiredArgsConstructor
final class PersonMappingProvider implements
  MappingProvider<Person, PersonData, CompositeContext> {
  private final PersonMapper personMapper;

  @Override
  public Mapping<Person, PersonData, CompositeContext> provide() {
    return AbstractCompositeContextMapping.mappingFor(
      Person.class, PersonData.class,
      personMapper::updateFromPerson
    );
  }
}
