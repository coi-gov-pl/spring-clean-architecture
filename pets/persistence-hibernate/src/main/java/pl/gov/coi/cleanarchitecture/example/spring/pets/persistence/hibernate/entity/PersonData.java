package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Entity
@Table
@Setter(AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
public class PersonData implements Person, HasRecord {

  private Record record = new Record();
  private String name;
  private String surname;
  private List<OwnershipData> ownershipList = new ArrayList<>();

  private PersonData(Person person) {
    setName(person.getName());
    setSurname(person.getSurname());
    ownershipList = new ArrayList<>();
    person.getOwnerships()
      .forEach(ownership ->
        ownershipList.add(OwnershipData.ensureIsData(ownership))
      );
  }

  @Override
  public void addOwnership(Ownership ownership) {
    ownershipList.add(OwnershipData.ensureIsData(ownership));
  }

  @Override
  public int getOwnershipCount() {
    return ownershipList.size();
  }

  @Override
  public boolean hasOwnerships() {
    return !ownershipList.isEmpty();
  }

  @Override
  public Iterable<Ownership> getOwnerships() {
    return Collections.unmodifiableList(ownershipList);
  }

  // JPA Mappings

  @Valid
  @Embedded
  @Override
  public Record getRecord() {
    return record;
  }

  @Column
  @Override
  public String getName() {
    return name;
  }

  @Column
  @Override
  public String getSurname() {
    return surname;
  }

  @OneToMany
  protected List<OwnershipData> getOwnershipsList() {
    return ownershipList;
  }

}
