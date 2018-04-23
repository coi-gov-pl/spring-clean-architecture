package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

import javax.validation.Validator;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2017-01-06
 */
@Configuration
class RegisterNewPetConfiguration {

  @Bean
  RegisterNewPetUseCase provideRegisterNewPetUseCase(PetsGateway petsGateway,
                                                     PersonGateway personGateway,
                                                     RegisterNewPetRequestToPetMapper mapper,
                                                     Validator validator) {
    return new RegisterNewPetUseCaseImpl(petsGateway, personGateway, mapper, validator);
  }

  @Bean
  EnumMapper<RegisterNewPetRequestModel.Race, Race> provideEnumMapper() {
    return new RaceEnumMapper();
  }


  @Bean
  RegisterNewPetRequestToPetMapper providePetMapper(
    EnumMapper<RegisterNewPetRequestModel.Race, Race> raceMapper) {

    return new RegisterNewPetRequestToPetMapper(raceMapper);
  }
}
