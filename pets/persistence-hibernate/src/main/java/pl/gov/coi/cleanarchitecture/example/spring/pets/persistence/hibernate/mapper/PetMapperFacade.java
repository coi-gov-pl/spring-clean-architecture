package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-12
 */
public interface PetMapperFacade {
  PetData map(Pet pet);
  Pet map(PetData data);
}
