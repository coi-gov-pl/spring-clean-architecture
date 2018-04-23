package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.wavesoftware.utils.stringify.ObjectStringifier;
import pl.wavesoftware.utils.stringify.annotation.Inspect;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-01-18
 */
@Setter(AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "id")
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class Record {

  @Inspect
  private Long id;
  private Long version;
  private Date created;
  private Date modified;

  @GenericGenerator(
    name = "idSequenceGenerator",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator"
  )
  @NotNull
  @Id
  @GeneratedValue(generator = "idSequenceGenerator")
  public Long getId() {
    return id;
  }

  @PrePersist
  private void beforePersistAbstractEntity() {
    Instant now = Instant.now();
    if (created == null) {
      created = Date.from(now);
    }
    modified = Date.from(now);
  }

  @PreUpdate
  private void beforeUpdateAbstractEntity() {
    modified = Date.from(Instant.now());
  }

  @Version
  @NotNull
  public Long getVersion() {
    return version;
  }

  @Column
  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  public Date getCreated() {
    return Date.from(created.toInstant());
  }

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  public Date getModified() {
    return Date.from(modified.toInstant());
  }

  @Override
  public String toString() {
    return new ObjectStringifier(this).toString();
  }
}
