package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import java.io.Serializable;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-03-17
 */
public interface Person extends Serializable {
  /**
   * Gets number of possessed ownership
   * @return a number of ownerships
   */
  int getOwnershipCount();

  /**
   * Return true if have any ownership
   * @return true if have any ownership
   */
  boolean hasOwnerships();

  /**
   * Get ownership iterable object
   * @return an ownerships
   */
  Iterable<Ownership> getOwnerships();

  /**
   * Getter for name
   * @return a name
   */
  String getName();

  /**
   * Getter for surname
   * @return a surname
   */
  String getSurname();

  /**
   * Adds an ownership by returning modified object
   *
   * @param ownership an ownership
   */
  void addOwnership(Ownership ownership);

  /**
   * Sets name in modified object
   *
   * @param name a name to set
   */
  void setName(String name);

  /**
   * Sets a surname in modified object
   *
   * @param surname a surname
   */
  void setSurname(String surname);
}
