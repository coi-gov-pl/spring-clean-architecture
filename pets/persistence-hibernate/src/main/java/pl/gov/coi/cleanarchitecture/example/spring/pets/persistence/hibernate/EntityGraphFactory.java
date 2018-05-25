package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.FormerOwnershipData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.FormerOwnershipData_;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.OwnershipData_;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PersonData_;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData_;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 17.05.18
 */
@RequiredArgsConstructor
final class EntityGraphFactory {
  private final Supplier<EntityManager> entityManager;

  EntityGraph<PetData> getPetWithOwnershipsEntityGraph() {
    EntityGraph<PetData> petGraph = entityManager.get().createEntityGraph(PetData.class);
    petGraph.addAttributeNodes(attrs(
      PetData_.ownership, PetData_.formerOwners
    ));
    Subgraph<OwnershipData> ownershipSubGraph = addOwnershipSubGraph(petGraph);
    addPetSubGraph(ownershipSubGraph, OwnershipData_.pet);
    addPersonSubGraph(ownershipSubGraph, OwnershipData_.person);

    Subgraph<FormerOwnershipData> formerOwnershipsGraph = addFormerOwnershipsGraph(petGraph);
    addPetSubGraph(formerOwnershipsGraph, FormerOwnershipData_.pet);
    Subgraph<PersonData> formerOwnerSubGraph = addPersonSubGraph(
      formerOwnershipsGraph, FormerOwnershipData_.person
    );
    Subgraph<OwnershipData> formerOwnerOwnershipsSubGraph =
      addOwnershipsSubGraph(formerOwnerSubGraph);
    addPetSubGraph(formerOwnerOwnershipsSubGraph, OwnershipData_.pet);
    addPersonSubGraph(formerOwnerOwnershipsSubGraph, OwnershipData_.person);

    return petGraph;
  }

  EntityGraph<PetData> getPetWithOwnerEntityGraph() {
    EntityGraph<PetData> petGraph = entityManager.get()
      .createEntityGraph(PetData.class);
    petGraph.addAttributeNodes(attrs(
      PetData_.ownership
    ));
    addOwnershipSubGraph(petGraph);
    return petGraph;
  }

  private Subgraph<OwnershipData> addOwnershipSubGraph(EntityGraph<PetData> petGraph) {
    Subgraph<OwnershipData> ownershipGraph = petGraph.addSubgraph(PetData_.ownership);
    ownershipGraph.addAttributeNodes(attrs(
      OwnershipData_.person, OwnershipData_.pet
    ));
    return ownershipGraph;
  }

  private Subgraph<FormerOwnershipData> addFormerOwnershipsGraph(EntityGraph<PetData> petGraph) {
    Subgraph<FormerOwnershipData> formerOwnershipsGraph = petGraph
      .addSubgraph(PetData_.formerOwners.getName(), FormerOwnershipData.class);
    formerOwnershipsGraph.addAttributeNodes(attrs(
      FormerOwnershipData_.person,
      FormerOwnershipData_.pet
    ));
    return formerOwnershipsGraph;
  }

  private Subgraph<OwnershipData> addOwnershipsSubGraph(Subgraph<PersonData> graph) {
    Subgraph<OwnershipData> ownershipsSubGraph =
      graph.addSubgraph(PersonData_.ownerships.getName(), OwnershipData.class);
    ownershipsSubGraph.addAttributeNodes(attrs(
      OwnershipData_.person, OwnershipData_.pet
    ));
    return ownershipsSubGraph;
  }

  private <T> Subgraph<PetData> addPetSubGraph(Subgraph<T> ownershipsGraph,
                                               SingularAttribute<T, PetData> attribute) {
    Subgraph<PetData> petSubGraph = ownershipsGraph.addSubgraph(attribute);
    petSubGraph.addAttributeNodes(attrs(
      PetData_.ownership,
      PetData_.formerOwners
    ));
    return petSubGraph;
  }

  private <T> Subgraph<PersonData> addPersonSubGraph(Subgraph<T> ownershipsGraph,
                                                     SingularAttribute<T, PersonData> attribute) {
    Subgraph<PersonData> personSubGraph = ownershipsGraph.addSubgraph(attribute);
    personSubGraph.addAttributeNodes(attrs(
      PersonData_.ownerships
    ));
    return personSubGraph;
  }

  @SafeVarargs
  private final <T> Attribute<T, ?>[] attrs(Attribute<T, ?>... attributes) {
    return attributes;
  }
}
