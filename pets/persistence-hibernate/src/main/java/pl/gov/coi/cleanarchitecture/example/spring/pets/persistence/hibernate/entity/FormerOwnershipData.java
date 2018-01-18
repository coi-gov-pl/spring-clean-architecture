package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@Entity
public class FormerOwnershipData extends OwnershipData {
  @NotNull
  private Date to;

  public FormerOwnershipData() {
    // nothing
  }

  public FormerOwnershipData(OwnershipData ownershipData) {
    to = new Date();
    setPerson(ownershipData.getPerson());
    setPet(ownershipData.getPet());
    setFrom(getFrom());
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_to")
  public Date getTo() {
    return to;
  }
}
