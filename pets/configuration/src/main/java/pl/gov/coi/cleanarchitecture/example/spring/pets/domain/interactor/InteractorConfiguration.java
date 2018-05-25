package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.interactor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@Configuration
public class InteractorConfiguration {
  @Bean
  LoadPetInteractor provideLoadPetInteractor(PetsGateway petsGateway,
                                             EnumMapper<PetContract.Race, Race> raceEnumMapper) {
    return new LoadPetInteractorImpl(petsGateway, raceEnumMapper);
  }
}
