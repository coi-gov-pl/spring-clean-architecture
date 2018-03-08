package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.immutable;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.mutable.PersonData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-09
 */
public class PersonMutablizer implements Mutablizer<Person, PersonData> {
  @Override
  public PersonData mutable(Person immutable) {
    return null;
  }
}
