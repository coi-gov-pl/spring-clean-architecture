package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PersonGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Profile("default")
@Configuration
class PersistenceConfiguration {
  @Bean
  public PetsGateway provide(StubDatabase database) {
    return new PetsGatewayStub(database);
  }

  @Bean
  public PersonGateway personGateway(StubDatabase database) {
    return new PersonGatewayStub(database);
  }

  @Bean
  public StubDatabase database() {
    return new SetStubDatabase();
  }
}

