package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Metadata;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.metadata.Reference;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 15.05.18
 */
public class AbstractEntityTest {

  private static final Long ANSWER_TO_ALL = 42L;

  @Test
  public void testCollection() {
    // given
    Set<ExampleEntity> entities = new LinkedHashSet<>();
    ExampleEntity zaq = new ExampleEntity("zaq");
    zaq.supplierOfMetadata(() -> createMetadata(ANSWER_TO_ALL));
    ExampleEntity qwe = new ExampleEntity("qwe");
    qwe.supplierOfMetadata(() -> createMetadata(0L));

    // when
    entities.add(zaq);
    entities.add(qwe);

    // then
    assertThat(entities).hasSize(2);

    // when
    entities.add(qwe);

    // then
    assertThat(entities).hasSize(2);

    // when
    qwe = new ExampleEntity("jordi");
    qwe.supplierOfMetadata(() -> createMetadata(0L));
    entities.remove(qwe);

    // then
    assertThat(entities).hasSize(1);
    assertThat(entities.iterator().next().getValue()).isEqualTo("zaq");
  }

  @Test
  public void testMetadata() {
    // given
    ExampleEntity entity = new ExampleEntity("qazwsx12");

    // when
    Optional<Reference> ref = entity.getMetadata()
      .get(Reference.class);

    // then
    assertThat(ref).isNotPresent();
    assertThat(entity.isMetadataSet()).isFalse();

    // when
    entity.supplierOfMetadata(() -> createMetadata(ANSWER_TO_ALL));
    ref = entity.getMetadata().get(Reference.class);

    // then
    assertThat(entity.isMetadataSet()).isTrue();
    assertThat(ref).isPresent();
    ref.ifPresent(r -> {
      assertThat(r.get()).isEqualTo(ANSWER_TO_ALL);
      assertThat(r.isEqualTo(() -> ANSWER_TO_ALL)).isTrue();
    });
  }

  @Test
  public void testToString() {
    // given
    ExampleEntity entity = new ExampleEntity("123456789oiuytrewq");

    // when
    String result = entity.toString();

    // then
    assertThat(result).isEqualTo("<ExampleEntity value=\"123456789oiuytrewq\">");
  }

  @Test
  public void testHashCode() {
    // given
    ExampleEntity entity1 = new ExampleEntity("same");
    ExampleEntity entity2 = new ExampleEntity("same");

    // then
    assertThat(entity1.hashCode())
      .isNotEqualTo(entity2.hashCode());

    // when
    entity1.supplierOfMetadata(() -> createMetadata(ANSWER_TO_ALL));
    entity2.supplierOfMetadata(() -> createMetadata(ANSWER_TO_ALL));

    // then
    assertThat(entity1.hashCode())
      .isEqualTo(entity2.hashCode());
    assertThat(entity1.hashCode()).isEqualTo(ANSWER_TO_ALL.hashCode());
  }

  @Test
  public void testEquals() {
    // given
    ExampleEntity entity1 = new ExampleEntity("other");
    ExampleEntity entity2 = new ExampleEntity("same");

    // then
    assertThat(entity1).isNotEqualTo(entity2);

    // when
    entity1.supplierOfMetadata(() -> createMetadata(1L));
    entity2.supplierOfMetadata(() -> createMetadata(1L));

    // then
    assertThat(entity1).isEqualTo(entity2);
  }

  private <T extends AbstractEntity> Metadata<T> createMetadata(long id) {
    @SuppressWarnings("unchecked")
    Metadata<T> metadata = mock(Metadata.class);
    when(metadata.get(Reference.class))
      .thenReturn(
        Optional.of(() -> id)
      );
    return metadata;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  private static final class ExampleEntity extends AbstractEntity<ExampleEntity> {

    private String value;
  }
}
