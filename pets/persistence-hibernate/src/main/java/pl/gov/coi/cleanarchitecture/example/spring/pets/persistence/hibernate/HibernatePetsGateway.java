package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.FetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.OnGoingFetching;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetFetchProfile;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.gateway.PetsGateway;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Paginated;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.Pagination;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData_;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper.MapperFacade;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.sql.QueryProvider;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.sql.QueryProvider.OnGoingQueryProviding;

import javax.annotation.Nullable;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
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

  private final Map<FetchProfile<Pet>, Supplier<EntityGraph<PetData>>> graphs
    = createEntityGraphs();

  @Override
  public OnGoingFetching<Pet> findByReference(Reference reference) {
    return profile -> {
      @Nullable
      EntityGraph<PetData> graph = getGraphByProfile(profile);
      return findDataByReference(reference, graph)
        .map(mapper::map);
    };
  }

  @Override
  public void update(Reference reference, Pet pet) {
    PetData petData = entityManager.find(PetData.class, reference.get());
    mapper.update(petData).with(pet);
    entityManager.persist(petData);
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
  public Iterable<Pet> persistNew(Pet... pets) {
    Collection<PetData> collection = mapper.map(Arrays.asList(pets));
    for (PetData data : collection) {
      entityManager.persist(data);
    }
    return mapper.reverseMap(collection);
  }

  private Optional<PetData> findDataByReference(Reference reference,
                                                @Nullable EntityGraph<PetData> entityGraph) {
    Serializable identifier = reference.get();
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<PetData> cq = cb.createQuery(PetData.class);
    Root<PetData> pet = cq.from(PetData.class);
    cq.where(
      cb.equal(
        pet.get(PetData_.id),
        cb.parameter(Long.class, "id")
      )
    );
    PetData petData = entityManager
      .createQuery(cq)
      .setParameter("id", identifier)
      .setHint("javax.persistence.loadgraph", entityGraph)
      .getSingleResult();
    return Optional.ofNullable(petData);
  }

  @Nullable
  private EntityGraph<PetData> getGraphByProfile(FetchProfile<Pet> fetchProfile) {
    Supplier<EntityGraph<PetData>> defaultValue = () -> null;
    return graphs
      .getOrDefault(fetchProfile, defaultValue)
      .get();
  }

  private static int calculateStartPosition(Pagination pagination) {
    return (pagination.getPageNumber() - 1) * pagination.getElementsPerPage();
  }

  private Map<FetchProfile<Pet>, Supplier<EntityGraph<PetData>>> createEntityGraphs() {
    EntityGraphFactory factory = new EntityGraphFactory(() -> entityManager);
    Map<FetchProfile<Pet>, Supplier<EntityGraph<PetData>>> creatingGraphs = new HashMap<>();
    creatingGraphs.put(PetFetchProfile.WITH_OWNERSHIPS, factory::getPetWithOwnershipsEntityGraph);
    creatingGraphs.put(PetFetchProfile.WITH_OWNER, factory::getPetWithOwnerEntityGraph);
    creatingGraphs.put(PetFetchProfile.SOLE, factory::getPetWithOwnerEntityGraph);
    return creatingGraphs;
  }

}
