package pl.gov.coi.cleanarchitecture.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@SpringBootApplication(exclude = AppMain.class)
public class AppMain {
  private static AppMain instance;
  private ConfigurableApplicationContext ctx;

  public static void main(String[] args) throws Exception {
    instance = new AppMain();
    instance.start(args);
  }

  public static void stop() {
    instance.close();
  }

  private void start(String[] args) {
    ctx = SpringApplication.run(AppMain.class, args);
  }

  private void close() {
    ctx.close();
  }
}
