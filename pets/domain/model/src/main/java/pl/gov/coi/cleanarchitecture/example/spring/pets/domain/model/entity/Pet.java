package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-17
 */
public interface Pet extends Serializable {
  /**
   * Getter for name
   * @return a name
   */
  String getName();

  /**
   * Getter for race
   * @return a race
   */
  Race getRace();

  /**
   * Gets ownership
   * @return an ownership
   */
  Optional<Ownership> getOwnership();

  /**
   * Gets former owners to iterate
   * @return an list of former owners
   */
  Iterable<FormerOwnership> getFormerOwners();

  /**
   * Return true is there are former owners
   * @return true if there are former owners
   */
  boolean hasFormerOwners();

  /**
   * Get number of former owner count
   * @return a number of former owner count
   */
  int getFormerOwnerCount();

  /**
   * Sets a name of pet
   * @param name a name
   */
  void setName(String name);

  /**
   * Sets a race of a pet
   * @param race a race
   */
  void setRace(Race race);

  /**
   * Sets owner
   * @param owner an owner
   */
  void setOwner(Person owner);
}
