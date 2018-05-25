package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.logic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 09.05.18
 */
@Configuration
class DomainLogicConfiguration {
  @Bean
  EntityReferenceMapper provideEntityReferenceMapper() {
    return new EntityReferenceMapperImpl();
  }
}
