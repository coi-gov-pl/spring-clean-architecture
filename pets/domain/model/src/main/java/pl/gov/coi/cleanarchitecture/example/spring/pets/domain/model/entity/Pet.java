package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Pet implements Serializable {

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
  public Pet(String name, Race race) {
    this.name = name;
    this.race = race;
  }

  public Iterable<FormerOwnership> getFormerOwners() {
    return Collections.unmodifiableList(getFormerOwnersList());
  }

  /**
   * Returns true if pet have any former owners
   *
   * @return true if pet have any former owners
   */
  public boolean hasFormerOwners() {
    return !getFormerOwnersList().isEmpty();
  }

  /**
   * Get number of former owners
   * @return number of former owners
   */
  public int getFormerOwnerCount() {
    return getFormerOwnersList().size();
  }

  public Optional<Ownership> getOwnership() {
    return Optional.ofNullable(ownership);
  }

  public void setOwner(Person owner) {
    Optional<Ownership> optOwnership = getOwnership();
    optOwnership.ifPresent(theOwnership -> {
      FormerOwnership formerOwnership = new FormerOwnership(theOwnership);
      getFormerOwnersList().add(formerOwnership);
    });
    Ownership newOwnership = new Ownership();
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
