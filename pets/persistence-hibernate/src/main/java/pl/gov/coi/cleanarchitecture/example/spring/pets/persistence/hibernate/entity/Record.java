package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.AccessLevel;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter
@Embeddable
public class Record {
  @NotNull
  private Long id;
  @Setter(AccessLevel.PROTECTED)
  private Long recordVersion;
  @NotNull
  private LocalDateTime created;
  @NotNull
  private LocalDateTime modified;

  Record() {
    // empty
  }

  @GenericGenerator(
    name = "idSequenceGenerator",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator"
  )
  @Id
  @GeneratedValue(generator = "idSequenceGenerator")
  public Long getId() {
    return id;
  }

  @PrePersist
  protected void beforePersistAbstractEntity() {
    LocalDateTime now = LocalDateTime.now();
    if (created == null) {
      created = now;
    }
    modified = now;
  }

  @PreUpdate
  protected void beforeUpdateAbstractEntity() {
    modified = LocalDateTime.now();
  }

  @Version
  public Long getRecordVersion() {
    return recordVersion;
  }

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  public LocalDateTime getCreated() {
    return created;
  }

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  public LocalDateTime getModified() {
    return modified;
  }
}
