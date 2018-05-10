package pl.gov.coi.cleanarchitecture.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.gov.coi.cleanarchitecture.example.spring.application.Application;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 10.05.18
 */
final class SpringCleanApplication implements Application {
  private final String[] args;
  private final Consumer<Application> onStop;
  private final Class<?> primarySource;
  @Nullable
  private ConfigurableApplicationContext ctx;

  SpringCleanApplication(Class<?> primarySource,
                         String[] args,
                         Consumer<Application> onStop) {
    this.primarySource = primarySource;
    this.args = new String[args.length];
    System.arraycopy(args, 0, this.args, 0, args.length);
    this.onStop = onStop;
  }

  @Override
  public void start() {
    ctx = SpringApplication.run(primarySource, args);
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
