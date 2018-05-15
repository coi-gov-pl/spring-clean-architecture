package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Pagination;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper.MapperFacade;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.sql.QueryProvider;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.sql.QueryProvider.OnGoingQueryProviding;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@RequiredArgsConstructor
final class HibernatePetsGateway implements PetsGateway {

  private final EntityManager entityManager;
  private final MapperFacade mapper;
  private final QueryProvider queryProvider;

  @Override
  public Optional<Pet> findByReference(Reference reference) {
    // TODO: implement this
    throw new UnsupportedOperationException("not yet implemented");
  }

  @Override
  public void update(Reference reference, Pet pet) {
    // TODO: implement this
    throw new UnsupportedOperationException("not yet implemented");
  }

  @Override
  public Paginated<Pet> getPets(Pagination pagination) {
    OnGoingQueryProviding queryProviding = queryProvider.forClass(HibernatePetsGateway.class);
    Query q = entityManager.createQuery(queryProviding.get("countPets"));
    long totalNumberOfElements = Long.class.cast(q.getSingleResult());
    TypedQuery<PetData> query = entityManager.createQuery(
      queryProviding.get("getPets"), PetData.class
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
    Collection<PetData> collection = mapper.map(Arrays.asList(pets));
    for (PetData data : collection) {
      entityManager.persist(data);
    }
  }

  private static int calculateStartPosition(Pagination pagination) {
    return (pagination.getPageNumber() - 1) * pagination.getElementsPerPage();
  }
}
