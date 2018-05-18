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
import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 17.05.18
 */
@RequiredArgsConstructor
final class EntityGraphFactory {
  private final Supplier<EntityManager> entityManager;

  EntityGraph<PetData> getPetWithOwnershipsEntityGraph() {
    EntityGraph<PetData> petGraph = entityManager.get()
      .createEntityGraph(PetData.class);
    petGraph.addAttributeNodes(attrs(
      PetData_.ownership, PetData_.formerOwners
    ));
    Subgraph<OwnershipData> ownershipSubGraph = petGraph.addSubgraph(PetData_.ownership);
    ownershipSubGraph.addAttributeNodes(attrs(
      OwnershipData_.person, OwnershipData_.pet
    ));
    Subgraph<PersonData> personSubGraph1 = ownershipSubGraph.addSubgraph(OwnershipData_.person);
    personSubGraph1.addAttributeNodes(attrs(
      PersonData_.ownerships
    ));
    Subgraph<PetData> petSubGraph1 = ownershipSubGraph.addSubgraph(OwnershipData_.pet);
    petSubGraph1.addAttributeNodes(attrs(
      PetData_.ownership, PetData_.formerOwners
    ));
    Subgraph<FormerOwnershipData> formerOwnerGraph = petGraph
      .addSubgraph(PetData_.formerOwners.getName(), FormerOwnershipData.class);
    formerOwnerGraph.addAttributeNodes(
      FormerOwnershipData_.person.getName(),
      FormerOwnershipData_.pet.getName()
    );
    /*
    Subgraph<PersonData> personSubGraph2 = formerOwnerGraph.addSubgraph(FormerOwnershipData_.person);
    personSubGraph2.addAttributeNodes(attrs(
      PersonData_.ownerships
    ));
    Subgraph<PetData> petSubGraph2 = formerOwnerGraph.addSubgraph(OwnershipData_.pet);
    petSubGraph2.addAttributeNodes(attrs(
      PetData_.ownership, PetData_.formerOwners
    ));
    */
    return petGraph;
  }

  EntityGraph<PetData> getPetWithOwnerEntityGraph() {
    EntityGraph<PetData> petGraph = entityManager.get()
      .createEntityGraph(PetData.class);
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
