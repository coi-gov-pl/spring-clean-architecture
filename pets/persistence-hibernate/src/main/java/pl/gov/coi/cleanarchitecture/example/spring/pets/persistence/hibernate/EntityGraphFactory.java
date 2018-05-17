package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData_;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData_;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.persistence.metamodel.Attribute;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 17.05.18
 */
@RequiredArgsConstructor
final class EntityGraphFactory {
  private final EntityManager entityManager;

  EntityGraph<PetData> getPetWithOwnershipsEntityGraph() {
    EntityGraph<PetData> petGraph = entityManager.createEntityGraph(PetData.class);
    petGraph.addAttributeNodes(attrs(
      PetData_.ownership, PetData_.formerOwners
    ));
    Subgraph<OwnershipData> ownershipGraph = petGraph.addSubgraph(PetData_.ownership);
    ownershipGraph.addAttributeNodes(attrs(
      OwnershipData_.person, OwnershipData_.pet
    ));
    Subgraph<OwnershipData> formerOwnerGraph = petGraph
      .addSubgraph(PetData_.formerOwners.getName(), OwnershipData.class);
    formerOwnerGraph.addAttributeNodes(attrs(
      OwnershipData_.person, OwnershipData_.pet
    ));
    return petGraph;
  }

  EntityGraph<PetData> getPetWithOwnerEntityGraph() {
    EntityGraph<PetData> petGraph = entityManager.createEntityGraph(PetData.class);
    petGraph.addAttributeNodes(attrs(
      PetData_.ownership
    ));
    Subgraph<OwnershipData> ownershipGraph = petGraph.addSubgraph(PetData_.ownership);
    ownershipGraph.addAttributeNodes(attrs(
      OwnershipData_.person, OwnershipData_.pet
    ));
    return petGraph;
  }

  @SafeVarargs
  private final <T> Attribute<T, ?>[] attrs(Attribute<T, ?>... attributes) {
    return attributes;
  }
}
