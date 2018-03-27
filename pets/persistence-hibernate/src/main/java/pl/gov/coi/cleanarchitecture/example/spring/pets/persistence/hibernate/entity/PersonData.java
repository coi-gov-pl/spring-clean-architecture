package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Data;
import lombok.Setter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Ownership;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Person;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Entity
@Table
@Setter
@Data
public class PersonData implements Person, HasRecord {

  private Record record;

  @Valid
  @Embedded
  @Override
  public Record getRecord() {
    return record;
  }

  @Override
  public int getOwnershipCount() {
    return 0;
  }

  @Override
  public boolean hasOwnerships() {
    return false;
  }

  @Override
  public Iterable<Ownership> getOwnerships() {
    return null;
  }

  @Column
  @Override
  public String getName() {
    return super.getName();
  }

  @Column
  @Override
  public String getSurname() {
    return super.getSurname();
  }

  @Override
  public void addOwnership(Ownership ownership) {

  }

  @Override
  public void setName(String name) {

  }

  @Override
  public void setSurname(String surname) {

  }

  @Override
  @OneToMany
  protected List<Ownership> getOwnershipsList() {
    return super.getOwnershipsList();
  }

}
