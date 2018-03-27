package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Data;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@Entity
@Table
@Data
public class PetData implements Pet, HasRecord {

  private static final long serialVersionUID = 20180307225158L;

  private Record record;
  private String name;
  private Race race;
  private Ownership ownership;
  private Set<FormerOwnership> formerOwners;

  public PetData() {
    // nothing
  }

  @Valid
  @Embedded
  @Override
  public Record getRecord() {
    return record;
  }

  @Override
  @Column
  public String getName() {
    return name;
  }

  @Override
  @Enumerated(EnumType.STRING)
  public Race getRace() {
    return race;
  }

  @Override
  public Optional<Ownership> getOwnership() {
    return Optional.empty();
  }

  @Override
  public Iterable<FormerOwnership> getFormerOwners() {
    return null;
  }

  @Override
  public boolean hasFormerOwners() {
    return false;
  }

  @Override
  public int getFormerOwnerCount() {
    return 0;
  }

  @Override
  public void setOwner(Person owner) {

  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  protected Ownership getNullableOwnership() {
    return super.getNullableOwnership();
  }

  @Override
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  protected List<FormerOwnership> getFormerOwnersList() {
    return super.getFormerOwnersList();
  }

}
