package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@RequiredArgsConstructor
final class PetsGatewayStub implements PetsGateway {
  private final StubDatabase database;
  private final ObjectSerializer<Pet> petObjectSerializer = new ObjectSerializer<>();

  @Override
  public Paginated<Pet> getPets(Pagination pagination) {
    List<Pet> elements = new ArrayList<>();
    long skip = (pagination.getPageNumber() - 1) * (long) pagination.getElementsPerPage();
    long i = 1;
    long collected = 0;
    for (Pet pet : database.getPets()) {
      if (i > skip) {
        if (collected < pagination.getElementsPerPage()) {
          elements.add(petObjectSerializer.refresh(pet));
          collected++;
        }
      } else {
        i++;
      }
    }
    PageInfo info = new PageInfo(pagination, database.getPets().size());
    return new Paginated<>(info, elements);
  }

  @Override
  public void persistNew(Pet... pets) {
    for (Pet pet : pets) {
      Pet refreshed = petObjectSerializer.refresh(pet);
      database.getPets().add(refreshed);
    }
  }

}
