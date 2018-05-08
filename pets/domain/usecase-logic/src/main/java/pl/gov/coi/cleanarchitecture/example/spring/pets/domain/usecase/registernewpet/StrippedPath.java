package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.registernewpet;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Will strip <code>elementsToStrip</code> in beginning of a path
 *
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 08.05.18
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
final class StrippedPath implements Path {

  private static final String PROPERTY_PATH_SEPARATOR = ".";

  private final int elementsToStrip;
  private final Path propertyPath;

  @Override
  @Nonnull
  public Iterator<Node> iterator() {
    Iterator<Node> iterator = propertyPath.iterator();
    for (int i = 0; i < elementsToStrip && iterator.hasNext(); i++) {
      iterator.next();
    }
    return iterator;
  }

  @Override
  public String toString() {
    String asString = propertyPath.toString();
    String[] splited = asString.split(Pattern.quote(PROPERTY_PATH_SEPARATOR));
    List<String> parts = new ArrayList<>();
    for (int i = 0; i < splited.length; i++) {
      if (i < elementsToStrip) {
        continue;
      }
      parts.add(splited[i]);
    }
    return parts
      .stream()
      .collect(Collectors.joining(PROPERTY_PATH_SEPARATOR));
  }
}
