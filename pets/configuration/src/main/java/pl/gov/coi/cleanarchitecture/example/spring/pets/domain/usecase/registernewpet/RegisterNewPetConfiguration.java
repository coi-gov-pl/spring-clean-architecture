package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.mapper.PetContractMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.transactional.TransactionalBeanProvider;

import javax.validation.Validator;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2017-01-06
 */
@Configuration
@EnableTransactionManagement
class RegisterNewPetConfiguration {

  @Bean
  RegisterNewPetUseCase provideUseCase(PetsGateway petsGateway,
                                       PersonGateway personGateway,
                                       PetContractMapper mapper,
                                       Validator validator,
                                       TransactionalBeanProvider transactionalBeanProvider) {
    return transactionalBeanProvider.transactional(
      () -> new RegisterNewPetUseCaseImpl(petsGateway, personGateway, mapper, validator)
    );
  }

}
