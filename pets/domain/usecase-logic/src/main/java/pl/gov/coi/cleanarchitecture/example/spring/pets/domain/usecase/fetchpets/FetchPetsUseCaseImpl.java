package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.PetContract;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.logic.EntityReferenceMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.mapper.EnumMapper;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Pagination;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
class FetchPetsUseCaseImpl implements FetchPetsUseCase {

  private final PetsGateway petsGateway;
  private final EnumMapper<PetContract.Race, Race> raceEnumMapper;
  private final EntityReferenceMapper entityReferenceMapper;

  FetchPetsUseCaseImpl(PetsGateway petsGateway,
                       EnumMapper<PetContract.Race, Race> raceEnumMapper,
                       EntityReferenceMapper entityReferenceMapper) {
    this.petsGateway = petsGateway;
    this.raceEnumMapper = raceEnumMapper;
    this.entityReferenceMapper = entityReferenceMapper;
  }

  @Override
  public void execute(FetchPetsRequest request, FetchPetsResponse response) {
    Pagination pagination = new Pagination(request.getMaxElements());
    Paginated<Pet> pets = petsGateway.getPets(pagination);
    List<FetchPetsResponseModel.Pet> limited = StreamSupport
      .stream(pets.getElements().spliterator(), false)
      .limit(request.getMaxElements())
      .map(this::toResponseModel)
      .collect(Collectors.toList());
    response.setPets(limited);
    response.setPageInfo(pets.getPageInfo());
  }

  private FetchPetsResponseModel.Pet toResponseModel(Pet pet) {
    FetchPetsResponseModel.Owner owner = null;
    Optional<Ownership> optionalOwnership = pet.getOwnership();
    if (optionalOwnership.isPresent()) {
      owner = new FetchPetsResponseModel.Owner(
        optionalOwnership.get().getPerson().getName(),
        optionalOwnership.get().getPerson().getSurname()
      );
    }
    return new FetchPetsResponseModel.Pet(
      identifierOf(pet),
      pet.getName(),
      raceEnumMapper.reverseMap(pet.getRace()),
      owner
    );
  }

  private EntityReference identifierOf(Pet pet) {
    return entityReferenceMapper.map(pet);
  }
}
