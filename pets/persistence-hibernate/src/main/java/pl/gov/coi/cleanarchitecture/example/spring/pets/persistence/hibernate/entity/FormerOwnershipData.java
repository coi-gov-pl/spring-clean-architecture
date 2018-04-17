package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FormerOwnershipData extends OwnershipData {

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
