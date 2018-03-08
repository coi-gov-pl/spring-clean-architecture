package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@ToString
@Builder
public class Person implements Serializable {

  private static final long serialVersionUID = 20180307221257L;

  @Getter
  private final String name;
  @Getter
  private final String surname;
  private final List<Ownership> ownerships;

  /**
   * Constructor with name and surname
   * @param name a name of person
   * @param surname a surname of person
   */
  public Person(String name, String surname) {
    this(name, surname, new ArrayList<>());
  }

  /**
   * A full constructor
   * @param name a name of person
   * @param surname a surname of person
   * @param ownerships a list of ownerships
   */
  private Person(String name, String surname, List<Ownership> ownerships) {
    this.name = name;
    this.surname = surname;
    this.ownerships = Collections.unmodifiableList(ownerships);
  }

  /**
   * Gets number of possessed ownerships
   * @return a number of ownerships
   */
  public int getOwnershipCount() {
    return ownerships.size();
  }

  /**
   * Returns true if given person have any ownership
   *
   * @return true if given person have any ownership
   */
  public boolean hasOwnerships() {
    return !ownerships.isEmpty();
  }

  public Iterable<Ownership> getOwnerships() {
    return Collections.unmodifiableList(ownerships);
  }
}
