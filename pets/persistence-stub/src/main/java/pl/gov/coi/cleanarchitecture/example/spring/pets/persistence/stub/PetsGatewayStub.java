package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor
final class PetsGatewayStub implements PetsGateway {
  private final StubDatabase database;
  private final ObjectSerializer<Pet> petObjectSerializer = new ObjectSerializer<>();

  @Override
  public Optional<Pet> findByReference(Reference reference) {
    for (Pet pet : database.getPets()) {
      Optional<Reference> thisRef = pet.getMetadata()
        .get(Reference.class);

      if (thisRef.isPresent() && thisRef.get().get().equals(reference.get())) {
        return Optional.of(
          refresh(pet)
        );
      }
    }
    return Optional.empty();
  }

  @Override
  public Paginated<Pet> getPets(Pagination pagination) {
    List<Pet> elements = new ArrayList<>();
    long skip = (pagination.getPageNumber() - 1) * (long) pagination.getElementsPerPage();
    long i = 1;
    long collected = 0;
    for (Pet pet : database.getPets()) {
      if (i > skip) {
        if (collected < pagination.getElementsPerPage()) {
          elements.add(refresh(pet));
          collected++;
        }
      } else {
        i++;
      }
    }
    PageInfo info = new PageInfo(pagination, database.getNumberOfPets());
    return new Paginated<>(info, elements);
  }

  @Override
  public void persistNew(Pet... pets) {
    for (Pet pet : pets) {
      Pet refreshed = petObjectSerializer.refresh(pet);
      database.putOrUpdate(refreshed);
    }
  }

  @Override
  public void update(Reference reference, Pet pet) {
    pet.getMetadata()
      .get(Reference.class)
      .ifPresent(r -> checkArgument(r.isEqualTo(reference), "20180515:093317"));
    database.putOrUpdate(pet);
  }

  private Pet refresh(Pet pet) {
    Pet refreshed = petObjectSerializer.refresh(pet);
    refreshed.supplierOfMetadata(pet::getMetadata);
    return refreshed;
  }

}
