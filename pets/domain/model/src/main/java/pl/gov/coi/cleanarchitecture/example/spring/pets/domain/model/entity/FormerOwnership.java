package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.ToString;

import java.time.Instant;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@ToString
public class FormerOwnership extends AbstractOwnership {
  private static final long serialVersionUID = 20180307020630L;

  private final long to;

  /**
   * Constructor
   *
   * @param pet    a pet
   * @param person an owner of the pet
   * @param from   a instant of time in which person became owner of this pet
   * @param to     a instant of time in which person became former owner of this pet
   */
  FormerOwnership(Pet pet, Person person, Instant from, Instant to) {
    super(pet, person, from);
    this.to = to.toEpochMilli();
  }

  public Instant getTo() {
    return Instant.ofEpochMilli(to);
  }
}
