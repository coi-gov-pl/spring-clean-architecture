package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.validator;

import org.springframework.stereotype.Component;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.validation.constraint.Capitalized;

import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 22.12.16
 */
@Component
class CapitalizedValidatorImpl implements CapitalizedValidator {
  private static final String EMPTY_STRING = "";
  private static final Pattern CAPITALIZED_REGEX
    = Pattern.compile("^[A-Z][a-z0-9]*[^A-Za-z0-9]*$");

  @Override
  public void initialize(Capitalized constraintAnnotation) {
    // do nothing here
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || EMPTY_STRING.equals(value)) {
      return false;
    }
    boolean allGood = true;
    for (String word : value.split("\\s+")) {
      boolean isWordOk = CAPITALIZED_REGEX
        .asPredicate()
        .test(word);
      if (!isWordOk) {
        allGood = false;
        break;
      }
    }
    return allGood;
  }

}
