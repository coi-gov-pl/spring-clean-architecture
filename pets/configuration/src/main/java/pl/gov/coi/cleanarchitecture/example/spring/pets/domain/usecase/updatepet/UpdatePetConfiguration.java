package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper.PetContractMapper;

import javax.validation.Validator;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@Configuration
class UpdatePetConfiguration {
  @Bean
  UpdatePetUseCase provideUpdatePetUseCase(PetContractMapper petContractMapper,
                                           PetsGateway petsGateway,
                                           PersonGateway personGateway,
                                           Validator validator) {
    return new UpdatePetUseCaseImpl(
      petContractMapper,
      petsGateway,
      personGateway,
      validator
    );
  }
}
