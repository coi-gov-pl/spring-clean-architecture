package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OwnershipData extends AbstractEntity {
  @Valid
  @NotNull
  private PetData pet;
  @Valid
  @NotNull
  private PersonData person;
  @NotNull
  private Date from;

  @OneToOne(mappedBy = "ownership")
  public PetData getPet() {
    return pet;
  }

  @OneToOne
  public PersonData getPerson() {
    return person;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_from")
  public Date getFrom() {
    return from;
  }
}
