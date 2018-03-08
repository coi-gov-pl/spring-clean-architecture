package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.ToString;

import java.time.Instant;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@ToString
public class Ownership extends AbstractOwnership {

  private static final long serialVersionUID = 20180307020359L;

  /**
   * Ownership constructor
   *
   * @param pet a pet
   * @param person a owner of the pet
   * @param from a instant of time in which person became owner of this pet
   */
  public Ownership(Pet pet, Person person, Instant from) {
    super(pet, person, from);
  }

  /**
   * Creates former ownership based of this current ownership
   *
   * @return a new former ownership
   */
  public FormerOwnership toFormerOwnership() {
    return new FormerOwnership(getPet(), getPerson(), getFrom(), Instant.now());
  }
}
