package pl.gov.coi.example.spring.cleanarchitecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@SpringBootApplication(exclude = Main.class)
public class Main {
  private static Main instance;
  private ConfigurableApplicationContext ctx;

  public static void main(String[] args) throws Exception {
    instance = new Main();
    instance.start(args);
  }

  public static void stop() {
    instance.close();
  }

  private void start(String[] args) {
    ctx = SpringApplication.run(Main.class, args);
  }

  private void close() {
    ctx.close();
  }
}
