package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;

import java.io.Serializable;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
public class AesEntityReferenceMapperTest {

  @Test
  public void testMap() {
    // given
    String key = "Ou8mohheeighi4ThaiK7aaJ5be6AcaedeeN2Eimooil5OaghEehahy3tcho9eaZu";
    ClassLocator classLocator = new ClassLocatorImpl();
    Serializer serializer = new LongOptimizedJavaSerializer();
    ClassNameEncoder classNameEncoder = new ClassNameEncoderImpl();
    EntityReferenceMapper referenceMapper = new AesEntityReferenceMapper(
      key,
      false,
      classLocator,
      serializer,
      classNameEncoder
    );
    EntityReference reference = new TestEntityReference(Car.class, 42L);

    // when
    CharSequence repr = referenceMapper.map(reference);
    Optional<EntityReference> result = referenceMapper.map(repr + "");

    // then
    assertThat(result).isPresent();
    result.ifPresent(r -> {
      assertThat(r.getReference()).isEqualTo(42L);
      assertThat(r.getType()).isEqualTo(Car.class);
    });
    assertThat(repr).isEqualTo("EEOJEuEP0dz0NOGqKOHnjg");
  }

  @Getter
  @RequiredArgsConstructor
  private static final class TestEntityReference implements EntityReference {
    private final Serializable type;
    private final Serializable reference;
  }

  private interface Car {
  }
}
