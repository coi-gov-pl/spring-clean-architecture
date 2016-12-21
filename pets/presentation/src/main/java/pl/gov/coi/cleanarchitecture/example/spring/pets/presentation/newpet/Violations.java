package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.newpet;

import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet.RegisterNewPetResponseModel;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 21.12.16
 */
final class Violations {
  private static final String EMPTY_STRING = "";
  private Map<String, String> map = new HashMap<>();

  Violations(Iterable<RegisterNewPetResponseModel.Violation> violations) {
    this.map = StreamSupport
      .stream(violations.spliterator(), false)
      .collect(Collectors.toMap(
        this::pathAsString,
        RegisterNewPetResponseModel.Violation::getMessage,
        this::violationMassagesMerger
      ));
  }

  boolean isSuccessful() {
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

  private String pathAsString(RegisterNewPetResponseModel.Violation violation) {
    return violation.getPath().toString();
  }
}
