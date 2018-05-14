package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.validation;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.response.Violation;

import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
public final class Violations {
  private static final String EMPTY_STRING = "";
  private final Map<String, String> map;

  public Violations(Iterable<Violation> violations,
                    Function<Violation, String> violationToPath) {
    this.map = StreamSupport
      .stream(violations.spliterator(), false)
      .collect(Collectors.toMap(
        violationToPath,
        Violation::getMessage,
        this::violationMassagesMerger
      ));
  }

  public boolean isSuccessful() {
    return map.isEmpty();
  }

  public String ifKeyPresent(String key, String returningText) {
    return map.containsKey(key)
      ? returningText
      : EMPTY_STRING;
  }

  public String get(String key) {
    return map.getOrDefault(key, EMPTY_STRING);
  }

  private String violationMassagesMerger(String message, String secondMessage) {
    return new StringJoiner(", ")
      .add(message)
      .add(secondMessage)
      .toString();
  }

}
