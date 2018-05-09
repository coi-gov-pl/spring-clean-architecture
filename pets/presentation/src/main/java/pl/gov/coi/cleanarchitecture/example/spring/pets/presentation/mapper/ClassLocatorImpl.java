package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 09.05.18
 */
@Service
@RequiredArgsConstructor
final class ClassLocatorImpl implements ClassLocator {

  @Override
  @SuppressWarnings("unchecked")
  public <T> Class<T> locateClassByName(String className) throws ClassNotFoundException {
    return (Class<T>) ClassUtils
      .forName(className, null);
  }
}
