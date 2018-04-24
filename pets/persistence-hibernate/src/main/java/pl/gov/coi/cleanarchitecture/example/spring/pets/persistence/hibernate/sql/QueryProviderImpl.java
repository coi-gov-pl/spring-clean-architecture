package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.sql;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 24.04.18
 */
@Service
final class QueryProviderImpl implements QueryProvider {
  private final Map<Object, String> resolved = new HashMap<>();
  @Override
  public OnGoingQueryProviding forClass(Class<?> markerClass) {
    return new OnGoingQueryProvidingImpl(markerClass, resolved);
  }

  @RequiredArgsConstructor
  private static final class OnGoingQueryProvidingImpl implements OnGoingQueryProviding {
    private static final String LINE_DELIMITER = "\n";
    private static final String EXTENSION = ".sql";
    private final Class<?> markerClass;
    private final Map<Object, String> resolved;

    @Override
    public String get(String filename) {
      Object key = computeKey(filename);
      return getOrPut(key, filename, this::load);
    }

    private String getOrPut(Object key,
                            String filename,
                            Function<String, String> loadFunction) {
      if (resolved.containsKey(key)) {
        return resolved.get(key);
      } else {
        String witExt = filename + EXTENSION;
        String loaded = loadFunction.apply(witExt);
        resolved.put(key, loaded);
        return loaded;
      }
    }

    private String load(String filename) {
      return new BufferedReader(
        new InputStreamReader(markerClass.getResourceAsStream(filename))
      ).lines()
        .collect(Collectors.joining(LINE_DELIMITER));
    }

    private Object computeKey(String filename) {
      return markerClass.toString() + filename;
    }
  }
}
