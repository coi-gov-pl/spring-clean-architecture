package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Table
@Entity
@NoArgsConstructor
@Data
public class OwnershipData implements HasRecord {

  private Record record = new Record();
  private PetData pet;
  private PersonData person;
  private Date from;

  @Valid
  @Embedded
  @Override
  public Record getRecord() {
    return record;
  }

  @Valid
  @NotNull
  @OneToOne(mappedBy = "ownership")
  public PetData getPet() {
    return pet;
  }

  @Valid
  @NotNull
  @OneToOne
  public PersonData getPerson() {
    return person;
  }

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_from")
  public Date getFrom() {
    return from;
  }
}
