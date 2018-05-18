package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-05-17
 */
public interface Examples {

  enum PetExample {
    FRODO,
    KITIE,
    FLAMER,
    TOM,
    HILLBURN
  }

  enum PersonExample {
    K_SUSZYNSKI,
    P_ANDERSON,
    L_LOHAN
  }

  Pet getPet(PetExample petExample);
  Person getPerson(PersonExample personExample);
}
