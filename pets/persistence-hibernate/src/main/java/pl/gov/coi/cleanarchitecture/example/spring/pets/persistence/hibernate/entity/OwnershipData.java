package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Table
@Entity
@NoArgsConstructor
@Data
@Setter(AccessLevel.PROTECTED)
public class OwnershipData implements HasRecord, Ownership {

  private Record record = new Record();
  private PetData petData;
  private PersonData personData;
  private Date fromDate;

  private OwnershipData(Ownership ownership) {
    setFrom(ownership.getFrom());
    setPet(ownership.getPet());
    setPerson(ownership.getPerson());
  }

  @Override
  public Pet getPet() {
    return petData;
  }

  @Override
  public Person getPerson() {
    return personData;
  }

  @Override
  public Instant getFrom() {
    return fromDate.toInstant();
  }

  @Override
  public void setPet(Pet pet) {
    petData = PetData.ensureIsData(pet);
  }

  @Override
  public void setPerson(Person owner) {
    personData = PersonData.ensureIsData(owner);
  }

  @Override
  public void setFrom(Instant from) {
    fromDate = Date.from(from);
  }

  // JPA Mappings

  @Valid
  @Embedded
  @Override
  public Record getRecord() {
    return record;
  }

  @Valid
  @NotNull
  @OneToOne(mappedBy = "ownership")
  public PetData getPetData() {
    return petData;
  }

  @Valid
  @NotNull
  @OneToOne
  public PersonData getPersonData() {
    return personData;
  }

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_from")
  public Date getDateFrom() {
    return fromDate;
  }
}
