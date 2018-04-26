package pl.gov.coi.cleanarchitecture.example.spring;

import org.junit.Assert;
import org.junit.Test;
import pl.gov.coi.cleanarchitecture.example.spring.application.Application;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 26.04.18
 */
public class AppMainTest {

  @Test
  public void testFromArgs() {
    // given
    String[] args = new String[]{"this", "is", "a", "test!"};

    // when
    Application app = AppMain.fromArgs(args);

    // then
    assertThat(app).isNotNull();
    assertThat(app.isRunning()).isFalse();
  }

  @Test
  public void testGetApplications() {
    // when
    Iterable<Application> apps = AppMain.getApplications();

    // then
    assertThat(apps).isEmpty();
  }
}
