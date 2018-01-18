package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.springframework.stereotype.Component;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Component
final class PetToPetDataMapperImpl implements PetToPetDataMapper {
  @Override
  public Pet map(PetData petData) {
    return null;
  }

  @Override
  public PetData map(Pet pet) {
    return null;
  }
}
