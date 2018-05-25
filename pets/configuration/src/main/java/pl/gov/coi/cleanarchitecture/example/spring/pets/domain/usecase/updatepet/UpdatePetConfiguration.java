package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper.PetContractMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.transactional.TransactionalBeanProvider;

import javax.validation.Validator;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@Configuration
@EnableTransactionManagement
class UpdatePetConfiguration {
  @Bean
  UpdatePetUseCase provideUpdatePetUseCase(PetContractMapper petContractMapper,
                                           PetsGateway petsGateway,
                                           PersonGateway personGateway,
                                           Validator validator,
                                           TransactionalBeanProvider transactionalBeanProvider) {
    return transactionalBeanProvider.transactional(
      () -> new UpdatePetUseCaseImpl(
        petContractMapper,
        petsGateway,
        personGateway,
        validator
      )
    );
  }
}
