package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Entity
@NoArgsConstructor
@Getter
@Setter
@Access(AccessType.PROPERTY)
public class FormerOwnershipData extends Record {

  private static final long serialVersionUID = 20180508112245L;

  private PetData pet;
  private PersonData person;
  private Date from;
  private Date to;

  FormerOwnershipData(OwnershipData ownershipData) {
    to = Date.from(Instant.now());
    setPerson(ownershipData.getPerson());
    setPet(ownershipData.getPet());
    setFrom(getFrom());
  }

  @Valid
  @NotNull
  @OneToOne(
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    mappedBy = "ownership"
  )
  public PetData getPet() {
    return pet;
  }

  @Valid
  @NotNull
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public PersonData getPerson() {
    return person;
  }

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_from")
  public Date getFrom() {
    return from;
  }

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_to")
  public Date getTo() {
    return to;
  }

}
