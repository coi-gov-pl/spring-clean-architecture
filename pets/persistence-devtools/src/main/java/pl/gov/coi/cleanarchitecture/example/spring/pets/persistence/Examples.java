package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence;

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

  Pet getPet(PetExample petExample);
}
