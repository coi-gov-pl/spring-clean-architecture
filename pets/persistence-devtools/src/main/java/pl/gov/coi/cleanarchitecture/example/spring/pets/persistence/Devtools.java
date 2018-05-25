package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
@Service
@RequiredArgsConstructor
final class Devtools {

  private final ExampleRepository exampleRepository;
  private final ExampleDataPredicate exampleDataPredicate;

  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEventHandler() {
    if (exampleDataPredicate.shouldCreateExamples()) {
      exampleRepository.createExamples();
    }
  }

}
