package pl.gov.coi.cleanarchitecture.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.gov.coi.cleanarchitecture.example.spring.application.Application;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

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
    return new AppImpl(args, AppMain::onStop);
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

  private static final class AppImpl implements Application {
    private final String[] args;
    private final Consumer<Application> onStop;
    @Nullable
    private ConfigurableApplicationContext ctx;

    private AppImpl(String[] args, Consumer<Application> onStop) {
      this.args = new String[args.length];
      System.arraycopy(args, 0, this.args, 0, args.length);
      this.onStop = onStop;
    }

    @Override
    public void start() {
      ctx = SpringApplication.run(AppMain.class, args);
    }

    @Override
    public void stop() {
      Optional.ofNullable(ctx)
        .ifPresent(ConfigurableApplicationContext::close);
      onStop.accept(this);
    }

    @Override
    public boolean isRunning() {
      return Optional.ofNullable(ctx).isPresent();
    }

  }
}
