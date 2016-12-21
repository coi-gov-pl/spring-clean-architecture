package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets;

import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetRequestModel;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@Service
class FetchPetsUseCaseImpl implements FetchPetsUseCase {

  private final PetsGateway petsGateway;
  private final EnumMapper<RegisterNewPetRequestModel.Race, Race> raceEnumMapper;

  @Inject
  public FetchPetsUseCaseImpl(PetsGateway petsGateway,
                              EnumMapper<RegisterNewPetRequestModel.Race, Race> raceEnumMapper) {
    this.petsGateway = petsGateway;
    this.raceEnumMapper = raceEnumMapper;
  }

  @Override
  public void execute(FetchPetsRequest request, FetchPetsResponse response) {
    Iterable<Pet> pets = petsGateway.getAllActive();
    List<FetchPetsResponseModel.Pet> limited = StreamSupport
      .stream(pets.spliterator(), false)
      .limit(request.getMaxElements())
      .map(this::toResponseModel)
      .collect(Collectors.toList());
    response.setPets(limited);
  }

  private FetchPetsResponseModel.Pet toResponseModel(Pet pet) {
    FetchPetsResponseModel.Owner owner = null;
    Optional<Ownership> optionalOwnership = Optional.ofNullable(pet.getOwnership());
    if (optionalOwnership.isPresent()) {
      owner = new FetchPetsResponseModel.Owner(
        optionalOwnership.get().getPerson().getName(),
        optionalOwnership.get().getPerson().getSurname()
      );
    }
    return new FetchPetsResponseModel.Pet(
      pet.getName(),
      raceEnumMapper.reverseMap(pet.getRace()),
      owner
    );
  }
}
