package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper.PetContractMapper;

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
                                                     PetContractMapper mapper,
                                                     Validator validator) {
    return new RegisterNewPetUseCaseImpl(petsGateway, personGateway, mapper, validator);
  }

}
