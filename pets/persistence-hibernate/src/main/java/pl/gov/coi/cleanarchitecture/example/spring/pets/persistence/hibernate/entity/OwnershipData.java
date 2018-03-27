package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.impl.PetImpl;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@Entity
public class OwnershipData implements HasRecord, Ownership {

  private Record record;

  @Valid
  @Embedded
  @Override
  public Record getRecord() {
    return record;
  }

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
  public PetImpl getPet() {
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
