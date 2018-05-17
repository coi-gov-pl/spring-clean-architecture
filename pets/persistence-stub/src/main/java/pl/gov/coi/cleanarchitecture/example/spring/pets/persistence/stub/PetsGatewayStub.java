package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.FetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.OnGoingFetching;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor
final class PetsGatewayStub implements PetsGateway {
  private final StubDatabase database;
  private final ObjectSerializer<Pet> objectSerializer = new ObjectSerializer<>();
  private final LazyObjectFactory lazyObjectFactory = new LazyObjectFactory();

  @Override
  public OnGoingFetching<Pet> findByReference(Reference reference) {
    return profile -> {
      for (Pet pet : database.getPets()) {
        Optional<Reference> thisRef = pet.getMetadata()
          .get(Reference.class);

        if (thisRef.isPresent() && thisRef.get().get().equals(reference.get())) {
          return Optional.of(
            filterByProfile(profile, objectSerializer.refresh(pet))
          );
        }
      }
      return Optional.empty();
    };
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
          elements.add(
            objectSerializer.refresh(pet)
          );
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
  public Iterable<Pet> persistNew(Pet... pets) {
    for (Pet pet : pets) {
      Pet refreshed = objectSerializer.refresh(pet);
      database.putOrUpdate(refreshed);
    }
    Stream<Pet> stream = StreamSupport.stream(
      database.getPets().spliterator(), false
    );
    return stream
      .map(objectSerializer::refresh)
      .collect(Collectors.toList());
  }

  @Override
  public void update(Reference reference, Pet pet) {
    pet.getMetadata()
      .get(Reference.class)
      .ifPresent(r -> checkArgument(r.isEqualTo(reference), "20180515:093317"));
    Pet refreshed = objectSerializer.refresh(pet);
    database.putOrUpdate(
      refreshed
    );
  }

  private Pet filterByProfile(FetchProfile<Pet> profile, Pet pet) {
    if (profile == PetFetchProfile.SOLE) {
      // simulate lazy load
      pet.setFormerOwners(newLazy(List.class, "20180517:113039"));
      pet.setOwnership(newLazy(Ownership.class, "20180517:113043"));
    }
    if (profile == PetFetchProfile.WITH_OWNER) {
      // simulate partial lazy load
      pet.setFormerOwners(newLazy(List.class, "20180517:122241"));
    }
    return pet;
  }

  private <I, T extends I> T newLazy(Class<I> cls, String eid) {
    return lazyObjectFactory.newLazy(cls, eid);
  }

}
