package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.FormerOwnership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;
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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@Entity
@Table
@Data
@NoArgsConstructor
public class PetData implements Pet, HasRecord {

  private static final long serialVersionUID = 20180307225158L;

  private Record record = new Record();
  private String name;
  private Race race;
  private OwnershipData ownership;
  private List<FormerOwnershipData> formerOwners = new ArrayList<>();

  private PetData(Pet pet) {
    setName(pet.getName());
    setRace(pet.getRace());
    pet.getOwnership()
      .map(OwnershipData::ensureIsData)
      .ifPresent(this::setOwnership);
    formerOwners = new ArrayList<>();
    pet.getFormerOwners()
      .forEach(formerOwnership ->
        formerOwners.add(FormerOwnershipData.ensureIsData(formerOwnership))
      );
  }

  @Override
  public Optional<Ownership> getOwnership() {
    return Optional.ofNullable(ownership);
  }

  @Override
  public Iterable<FormerOwnership> getFormerOwners() {
    List<? extends FormerOwnership> tmpList = formerOwners;
    return Collections.unmodifiableList(tmpList);
  }

  @Override
  public boolean hasFormerOwners() {
    return Optional.ofNullable(formerOwners).isPresent()
      && !formerOwners.isEmpty();
  }

  @Override
  public int getFormerOwnerCount() {
    return Optional.ofNullable(formerOwners)
      .map(Collection::size)
      .orElse(0);
  }

  @Override
  public void setOwner(Person owner) {
    PersonData personData = PersonData.ensureIsData(owner);
    OwnershipData ownershipData = new OwnershipData();
    ownershipData.setFrom(Instant.now());
    ownershipData.setPersonData(personData);
    ownershipData.setPetData(this);
    Optional.ofNullable(ownership)
      .ifPresent(this::addAsFormerOwner);
  }

  // JPA Mappings

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

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  protected OwnershipData getNullableOwnership() {
    return ownership;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  protected List<FormerOwnershipData> getFormerOwnersList() {
    return formerOwners;
  }

  // Private

  private void addAsFormerOwner(OwnershipData ownershipData) {
    FormerOwnershipData former = new FormerOwnershipData(ownershipData);
    if (formerOwners == null) {
      formerOwners = new ArrayList<>();
    }
    formerOwners.add(former);
  }

}
