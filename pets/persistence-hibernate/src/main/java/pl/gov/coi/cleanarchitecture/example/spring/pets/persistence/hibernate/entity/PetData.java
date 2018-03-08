package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@Entity
@Table
public class PetData extends Pet {

  private static final long serialVersionUID = 20180307225158L;

  @Valid
  private Record record;

  public PetData() {
    this(null, null);
  }

  public PetData(String name,
                 Race race,
                 Ownership ownership,
                 List<FormerOwnership> formerOwners) {
    super(name, race, ownership, formerOwners);
  }

  private PetData(String name,
                  Race race) {
    super(name, race);
  }

  @Embedded
  public Record getRecord() {
    return record;
  }

  @Override
  @Column
  public String getName() {
    return super.getName();
  }

  @Override
  @Enumerated(EnumType.STRING)
  public Race getRace() {
    return super.getRace();
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public OwnershipData getOwnership() {
    return super.getOwnership();
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public List<FormerOwnershipData> getFormerOwnerships() {
    return new ArrayList<>(formerOwnerships);
  }

  public void setFormerOwnerships(List<FormerOwnershipData> formerOwnerships) {
    this.formerOwnerships = new ArrayList<>(formerOwnerships);
  }
}
