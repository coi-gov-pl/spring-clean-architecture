package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.NotLoaded;
import pl.wavesoftware.utils.stringify.configuration.DoNotInspect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-17
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Person extends AbstractEntity<Person> implements Serializable {

  private static final long serialVersionUID = 20180307221257L;

  private String name;
  private String surname;
  @DoNotInspect(conditionally = NotLoaded.class)
  private List<Ownership> ownerships = new ArrayList<>();

  /**
   * Default constructor
   *
   * @param name a name
   * @param surname a surname
   */
  public Person(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  /**
   * Gets number of possessed ownerships
   * @return a number of ownerships
   */
  public int getOwnershipCount() {
    return getOwnershipList().size();
  }

  /**
   * Returns true if given person have any ownership
   *
   * @return true if given person have any ownership
   */
  public boolean hasOwnerships() {
    return !getOwnershipList().isEmpty();
  }

  public Iterable<Ownership> getOwnerships() {
    return Collections.unmodifiableList(getOwnershipList());
  }

  public void addOwnership(Ownership ownership) {
    getOwnershipList().add(ownership);
    if (ownership.getPerson() != this) {
      ownership.setPerson(this);
    }
  }

  void removeOwnership(Ownership ownership) {
    getOwnershipList().remove(ownership);
  }

  private List<Ownership> getOwnershipList() {
    if (ownerships == null) {
      ownerships = new ArrayList<>();
    }
    return ownerships;
  }

}
