package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
public class ClassNameEncoderImplTest {

  @Test
  public void test() {
    // given
    ClassNameEncoder classNameEncoder = new ClassNameEncoderImpl();
    String fqcn = getClass().getName();

    // when
    String repr1 = classNameEncoder.getReprForClassName(fqcn);
    String repr2 = classNameEncoder.getReprForClassName(fqcn);

    String resultFqcn = classNameEncoder.getClassNameFromRepr(repr1);

    // then
    assertThat(repr1).isEqualTo(repr2);
    assertThat(resultFqcn).isEqualTo(fqcn);
    assertThat(repr1).isEqualTo("19h60y0");
  }
}
