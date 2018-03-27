package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class PersonImpl implements Person {

  private static final long serialVersionUID = 20180307221257L;

  private String name;
  private String surname;
  private List<Ownership> ownerships = new ArrayList<>();

  /**
   * Default constructor
   *
   * @param name a name
   * @param surname a surname
   */
  public PersonImpl(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  /**
   * Gets number of possessed ownerships
   * @return a number of ownerships
   */
  @Override
  public int getOwnershipCount() {
    return ownerships.size();
  }

  /**
   * Returns true if given person have any ownership
   *
   * @return true if given person have any ownership
   */
  @Override
  public boolean hasOwnerships() {
    return !ownerships.isEmpty();
  }

  @Override
  public void addOwnership(Ownership ownership) {
    getOwnershipList().add(ownership);
    if (ownership.getPerson() != this) {
      ownership.setPerson(this);
    }
  }

  private List<Ownership> getOwnershipList() {
    if (ownerships == null) {
      ownerships = new ArrayList<>();
    }
    return ownerships;
  }

}
