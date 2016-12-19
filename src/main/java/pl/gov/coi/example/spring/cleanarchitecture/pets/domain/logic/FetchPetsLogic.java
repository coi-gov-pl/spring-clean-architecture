package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.logic;

import org.springframework.stereotype.Service;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Pet;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.gateway.PetsGateway;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.fetchpets.FetchPetsUseCase;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.fetchpets.FetchPetsRequest;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.fetchpets.FetchPetsResponse;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@Service
class FetchPetsLogic implements FetchPetsUseCase {

  private final PetsGateway petsGateway;

  @Inject
  public FetchPetsLogic(PetsGateway petsGateway) {
    this.petsGateway = petsGateway;
  }

  @Override
  public void execute(FetchPetsRequest request, FetchPetsResponse response) {
    Iterable<Pet> pets = petsGateway.getAll();
    List<Pet> limited = StreamSupport
      .stream(pets.spliterator(), false)
      .limit(request.getMaxElements())
      .collect(Collectors.toList());
    response.setPets(limited);
  }
}
