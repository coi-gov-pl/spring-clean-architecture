package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
public interface ExampleData {
  void createExamples(PetsGateway petsGateway);
}
