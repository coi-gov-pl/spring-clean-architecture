package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class PetImpl implements Pet {

  private static final long serialVersionUID = 20180308212350L;

  private String name;
  private Race race;
  private Ownership ownership;
  private List<FormerOwnership> formerOwners = new ArrayList<>();

  /**
   * Default constructor
   *
   * @param name a name
   * @param race a race
   */
  public PetImpl(String name, Race race) {
    this.name = name;
    this.race = race;
  }

  @Override
  public Iterable<FormerOwnership> getFormerOwners() {
    return Collections.unmodifiableList(getFormerOwnersList());
  }

  /**
   * Returns true if pet have any former owners
   *
   * @return true if pet have any former owners
   */
  @Override
  public boolean hasFormerOwners() {
    return !getFormerOwnersList().isEmpty();
  }

  /**
   * Get number of former owners
   * @return number of former owners
   */
  @Override
  public int getFormerOwnerCount() {
    return getFormerOwnersList().size();
  }

  @Override
  public Optional<Ownership> getOwnership() {
    return Optional.ofNullable(ownership);
  }

  @Override
  public void setOwner(Person owner) {
    Optional<Ownership> optOwnership = getOwnership();
    optOwnership.ifPresent(theOwnership -> {
      FormerOwnership formerOwnership = new FormerOwnershipImpl(theOwnership);
      getFormerOwnersList().add(formerOwnership);
    });
    Ownership newOwnership = new OwnershipImpl();
    newOwnership.setPet(this);
    newOwnership.setPerson(owner);
    owner.addOwnership(newOwnership);
    ownership = newOwnership;
  }

  private List<FormerOwnership> getFormerOwnersList() {
    if (formerOwners == null) {
      formerOwners = new ArrayList<>();
    }
    return formerOwners;
  }

}
