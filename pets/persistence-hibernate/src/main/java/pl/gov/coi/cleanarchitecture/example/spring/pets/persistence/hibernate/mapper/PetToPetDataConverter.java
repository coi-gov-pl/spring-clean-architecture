package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
public interface PetToPetDataConverter {
  /**
   * Converts a pet to entity object
   * @param pet a pet
   * @return a pet data object
   */
  PetData convert(Pet pet);
}
