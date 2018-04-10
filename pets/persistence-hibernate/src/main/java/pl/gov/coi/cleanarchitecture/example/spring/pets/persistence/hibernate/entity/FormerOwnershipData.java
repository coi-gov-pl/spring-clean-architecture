package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@EqualsAndHashCode(callSuper = true)
@Setter(AccessLevel.PROTECTED)
@Entity
@Table
@Data
@NoArgsConstructor
public class FormerOwnershipData extends OwnershipData implements FormerOwnership {
  @NotNull
  private Date toDate;

  FormerOwnershipData(OwnershipData ownershipData) {
    toDate = new Date();
    setPerson(ownershipData.getPerson());
    setPet(ownershipData.getPet());
    setFrom(getFrom());
  }

  static FormerOwnershipData ensureIsData(FormerOwnership formerOwnership) {

  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_to")
  public Date getToDate() {
    return toDate;
  }

  @Override
  public Instant getTo() {
    return toDate.toInstant();
  }

  @Override
  public void setTo(Instant to) {
    toDate = Date.from(to);
  }

  @Override
  public void setPet(Pet pet) {
    if (pet instanceof PetData) {
      super.setPet(pet);
    } else {

    }
  }

  @Override
  public void setPerson(Person owner) {

  }
}
