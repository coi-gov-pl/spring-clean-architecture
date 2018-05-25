package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.stub;

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
    assertThat(alice.hashCode()).isEqualTo(1);
    assertThat(johnie.hashCode()).isEqualTo(2);
    assertThat(johnie).isNotEqualTo(alice);

    // when
    Supplier<Metadata<Pet>> meta = johnie::getMetadata;
    anne.supplierOfMetadata(meta);
    assertThat(anne.hashCode()).isEqualTo(2);
    assertThat(johnie).isEqualTo(anne);
    db.putOrUpdate(anne);

    // then
    assertThat(db.getPets()).hasSize(2);
  }
}
