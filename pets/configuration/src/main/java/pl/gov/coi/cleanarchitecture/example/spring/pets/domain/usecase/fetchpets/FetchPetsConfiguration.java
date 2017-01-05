package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetRequestModel;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2017-01-06
 */
@Configuration
class FetchPetsConfiguration {
  @Bean
  FetchPetsUseCase provideFetchPetsUseCase(PetsGateway petsGateway,
                           EnumMapper<RegisterNewPetRequestModel.Race, Race> raceEnumMapper) {
    return new FetchPetsUseCaseImpl(petsGateway, raceEnumMapper);
  }
}
