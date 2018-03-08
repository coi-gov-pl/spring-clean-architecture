package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OwnershipData extends Ownership {
  @Valid
  private Record record;
  /*
  @Valid
  @NotNull
  private PetData pet;
  @Valid
  @NotNull
  private PersonData person;
  @NotNull
  private LocalDateTime from;
  */

  /**
   * Hibernate constructor
   */
  public OwnershipData() {
    super(null, null, null);
  }

  @Override
  @OneToOne(mappedBy = "ownership")
  public Pet getPet() {
    return super.getPet();
  }

  @OneToOne
  public PersonData getPerson() {
    return person;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_from")
  public LocalDateTime getFrom() {
    return from;
  }
}
