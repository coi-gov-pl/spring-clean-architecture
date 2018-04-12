package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Entity
@Table
@Data
@NoArgsConstructor
public class PersonData implements HasRecord {

  private Record record = new Record();
  private String name;
  private String surname;
  private List<OwnershipData> ownerships = new ArrayList<>();

  // JPA Mappings

  @Valid
  @Embedded
  @Override
  public Record getRecord() {
    return record;
  }

  @Column
  public String getName() {
    return name;
  }

  @Column
  public String getSurname() {
    return surname;
  }

  @OneToMany
  protected List<OwnershipData> getOwnerships() {
    return ownerships;
  }

}
