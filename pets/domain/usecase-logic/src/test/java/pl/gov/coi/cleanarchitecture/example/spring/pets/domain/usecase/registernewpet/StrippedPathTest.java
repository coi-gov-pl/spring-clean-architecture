package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
public class StrippedPathTest {

  @Test
  public void testIterator() {
    // given
    Path path = PathImpl.createPathFromString("pet.name");
    Path stripped = new StrippedPath(1, path);

    // then
    assertThat(stripped).hasSize(1);
  }

  @Test
  public void testToString() {
    // given
    Path path = PathImpl.createPathFromString("form.pet.name");
    Path stripped = new StrippedPath(2, path);

    // when
    String result = stripped.toString();

    // then
    assertThat(result).isEqualTo("name");
  }
}
