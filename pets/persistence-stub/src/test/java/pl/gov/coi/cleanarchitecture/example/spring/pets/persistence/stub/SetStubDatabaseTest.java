package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

import org.junit.Ignore;
import org.junit.Test;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Race;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Metadata;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 15.05.18
 */
public class SetStubDatabaseTest {

  @Test
  @Ignore("Tests do not passes yet")
  public void testPutOrUpdate() {
    // given
    StubDatabase db = new SetStubDatabase();
    Pet alice = new Pet("Alice", Race.CAT);
    Pet johnie = new Pet("Johnie", Race.PIG);
    Pet anne = new Pet("Anne", Race.PIG);

    // when
    db.putOrUpdate(alice);
    db.putOrUpdate(johnie);

    // then
    assertThat(db.getPets()).hasSize(2);
    assertThat(johnie.hashCode()).isEqualTo(2);

    // when
    Supplier<Metadata<Pet>> meta = johnie::getMetadata;
    anne.supplierOfMetadata(meta);
    assertThat(anne.hashCode()).isEqualTo(2);
    db.putOrUpdate(anne); // FIXME: tests fails here with [20180515:161804]

    // then
    assertThat(db.getPets()).hasSize(2);
  }
}
