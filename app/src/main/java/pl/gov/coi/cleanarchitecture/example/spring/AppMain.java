package pl.gov.coi.cleanarchitecture.example.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.gov.coi.cleanarchitecture.example.spring.application.Application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@SpringBootApplication
public class AppMain {
  private static final Set<Application> CREATED_APPS = new HashSet<>();

  public static void main(String[] args) {
    startNewApp(args);
  }

  /**
   * Create an app
   * @param args an CLI args
   * @return a created app
   */
  static Application fromArgs(String[] args) {
    return new SpringCleanApplication(
      AppMain.class,
      args,
      AppMain::onStop
    );
  }

  /**
   * Gets a set of created apps
   * @return a set of apps
   */
  static Iterable<Application> getApplications() {
    return Collections.unmodifiableSet(CREATED_APPS);
  }

  private static void startNewApp(String[] args) {
    Application app = fromArgs(args);
    synchronized (CREATED_APPS) {
      CREATED_APPS.add(app);
      app.start();
    }
  }

  private static void onStop(Application application) {
    CREATED_APPS.remove(application);
  }

}
