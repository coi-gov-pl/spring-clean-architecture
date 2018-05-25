package pl.gov.coi.cleanarchitecture.example.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import pl.gov.coi.cleanarchitecture.example.spring.application.Application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
@Slf4j
public class AppMainIT {

  private static final int TIMEOUT_IN_SEC = 600;

  @Test
  public void testMain() throws IOException {
    // given
    int freePort = getFreePort();
    try (ServerPortPropertySetter ignored = new ServerPortPropertySetter(freePort)) {
      String[] args = new String[0];

      // when
      new Thread(() -> AppMain.main(args)).start();
      await()
        .atMost(TIMEOUT_IN_SEC, SECONDS)
        .until(() -> AppMain.getApplications().iterator().hasNext());
      assertThat(AppMain.getApplications()).isNotEmpty();
      await()
        .atMost(TIMEOUT_IN_SEC, SECONDS)
        .until(() -> isListening(freePort));

      // then
      assertThat(ignored).isNotNull();
      Application app = AppMain.getApplications().iterator().next();
      assertThat(app).isNotNull();
      app.stop();
      await()
        .atMost(TIMEOUT_IN_SEC, SECONDS)
        .until(() -> !isListening(freePort));
      await()
        .atMost(TIMEOUT_IN_SEC, SECONDS)
        .until(() -> !AppMain.getApplications().iterator().hasNext());
    }

  }

  private static int getFreePort() throws IOException {
    try(ServerSocket s = new ServerSocket(0)) {
      return s.getLocalPort();
    }
  }

  private static boolean isListening(int port) {
    try (Socket ignored = new Socket("localhost", port)) {
      assertThat(ignored).isNotNull();
      return true;
    } catch (IOException ex) {
      log.trace("Sinking", ex);
      return false;
    }
  }

  private static final class ServerPortPropertySetter implements AutoCloseable {

    private static final String SERVER_PORT = "server.port";

    private ServerPortPropertySetter(int port) {
      System.setProperty(SERVER_PORT, String.valueOf(port));
    }

    @Override
    public void close() {
      System.clearProperty(SERVER_PORT);
    }
  }

}
