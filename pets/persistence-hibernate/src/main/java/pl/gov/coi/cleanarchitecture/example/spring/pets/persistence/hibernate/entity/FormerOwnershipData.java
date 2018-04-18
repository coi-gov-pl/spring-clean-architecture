package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.Inspect;

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
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class FormerOwnershipData extends OwnershipData {

  @Inspect
  private Date to;

  FormerOwnershipData(OwnershipData ownershipData) {
    to = Date.from(Instant.now());
    setPerson(ownershipData.getPerson());
    setPet(ownershipData.getPet());
    setFrom(getFrom());
  }

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_to")
  public Date getTo() {
    return to;
  }

}
