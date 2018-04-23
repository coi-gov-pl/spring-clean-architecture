package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.scope.Pagination;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper.MapperFacade;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@RequiredArgsConstructor
final class HibernatePetsGateway implements PetsGateway {

  private final EntityManager entityManager;
  private final MapperFacade mapper;

  @Override
  public Paginated<Pet> getPets(Pagination pagination) {
    Query q = entityManager.createQuery("SELECT count(p.id) FROM PetData p");
    long totalNumberOfElements = Long.class.cast(q.getSingleResult());
    TypedQuery<PetData> query = entityManager.createQuery(
      "SELECT p " +
        "FROM PetData p " +
        "LEFT JOIN FETCH p.ownership o " +
        "LEFT JOIN FETCH o.person pp " +
        "ORDER BY p.id ASC", PetData.class
    );
    query.setMaxResults(pagination.getElementsPerPage());
    query.setFirstResult(calculateStartPosition(pagination));
    List<PetData> results = query.getResultList();
    List<Pet> elements = results.stream()
      .map(mapper::map)
      .collect(Collectors.toList());
    PageInfo info = new PageInfo(pagination, totalNumberOfElements);
    return new Paginated<>(info, elements);
  }

  @Override
  public void persistNew(Pet... pets) {
    List<PetData> petDataList = mapper.map(Arrays.asList(pets));
    for (PetData data : petDataList) {
      entityManager.persist(data);
    }
  }

  private static int calculateStartPosition(Pagination pagination) {
    return (pagination.getPageNumber() - 1) * pagination.getElementsPerPage();
  }
}
