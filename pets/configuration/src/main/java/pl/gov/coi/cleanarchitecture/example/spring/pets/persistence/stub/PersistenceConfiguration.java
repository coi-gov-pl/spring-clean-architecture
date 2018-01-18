package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.ExampleData;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Profile("default")
@Configuration
class PersistenceConfiguration {
  @Bean
  public PetsGateway provide(ExampleData exampleData) {
    PetsGateway stub = new PetsGatewayStub();
    exampleData.createExamples(stub);
    return stub;
  }
}

