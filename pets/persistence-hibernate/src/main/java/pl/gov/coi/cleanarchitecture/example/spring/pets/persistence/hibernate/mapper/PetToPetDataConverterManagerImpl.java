package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.springframework.stereotype.Component;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 10.04.18
 */
@Component
final class PetToPetDataConverterManagerImpl implements
  ConverterManager<Pet, PetData, PetToPetDataConverter> {

  @Override
  public PetToPetDataConverter open() {
    return new PetToPetDataConverterImpl();
  }
}
