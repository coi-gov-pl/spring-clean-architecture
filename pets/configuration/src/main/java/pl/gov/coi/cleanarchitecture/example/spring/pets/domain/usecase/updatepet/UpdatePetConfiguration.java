package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
@Configuration
class UpdatePetConfiguration {
  @Bean
  UpdatePetUseCase provideUpdatePetUseCase() {
    return new UpdatePetUseCaseImpl();
  }
}
