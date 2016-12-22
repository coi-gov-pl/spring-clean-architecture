package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.validator;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.constraint.Capitalized;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.12.16
 */
public class CapitalizedValidatorImplTest {
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock
  private ConstraintValidatorContext context;

  @Test
  public void testInitialize() throws IllegalAccessException, InstantiationException {
    // given
    CapitalizedValidatorImpl validator = new CapitalizedValidatorImpl();

    // when
    validator.initialize(newCapitalized());

    // then
    assertThat(validator).isNotNull();
  }

  @Test
  public void testIsValid() {
    // given
    CapitalizedValidatorImpl validator = new CapitalizedValidatorImpl();
    String target = "Johnie";

    // when
    boolean valid = validator.isValid(target, context);

    // then
    assertThat(valid).isTrue();
  }

  @Test
  public void testIsValid_invalid() {
    // given
    CapitalizedValidatorImpl validator = new CapitalizedValidatorImpl();
    String target = "maggie";

    // when
    boolean valid = validator.isValid(target, context);

    // then
    assertThat(valid).isFalse();
  }

  @Test
  public void testIsValid_Multiple() {
    // given
    CapitalizedValidatorImpl validator = new CapitalizedValidatorImpl();
    String target = "John Doe";

    // when
    boolean valid = validator.isValid(target, context);

    // then
    assertThat(valid).isTrue();
  }

  @Test
  public void testIsValid_MultipleInvalid() {
    // given
    CapitalizedValidatorImpl validator = new CapitalizedValidatorImpl();
    String target = "John van Doe";

    // when
    boolean valid = validator.isValid(target, context);

    // then
    assertThat(valid).isFalse();
  }

  @Test
  public void testIsValid_specialChars() {
    // given
    CapitalizedValidatorImpl validator = new CapitalizedValidatorImpl();
    String target = "Johnnie!";

    // when
    boolean valid = validator.isValid(target, context);

    // then
    assertThat(valid).isTrue();
  }

  private Capitalized newCapitalized() {
    return new Capitalized() {

      @Override
      public Class<? extends Annotation> annotationType() {
        return Capitalized.class;
      }

      @Override
      public String message() {
        return "";
      }

      @Override
      public Class<?>[] groups() {
        return new Class<?>[0];
      }

      @Override
      public Class<? extends Payload>[] payload() {
        return null;
      }
    };
  }

}
