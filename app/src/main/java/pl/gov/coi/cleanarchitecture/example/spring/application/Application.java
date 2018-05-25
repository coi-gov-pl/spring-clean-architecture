package pl.gov.coi.cleanarchitecture.example.spring.application;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
public interface Application {
  void start();
  void stop();
  boolean isRunning();
}
