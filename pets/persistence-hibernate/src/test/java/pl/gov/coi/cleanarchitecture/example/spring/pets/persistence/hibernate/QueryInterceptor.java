package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 17.05.18
 */
@Component
@RequiredArgsConstructor
final class QueryInterceptor
  implements HibernatePropertiesCustomizer, StatementInspector {

  private final ThreadLocal<List<String>> queries = ThreadLocal.withInitial(ArrayList::new);

  void clear() {
    queries.remove();
  }

  List<String> getExecutedQueries() {
    return Collections.unmodifiableList(
      queries.get()
    );
  }

  @Override
  public String inspect(String sql) {
    queries.get().add(sql);
    return sql;
  }

  @Override
  public void customize(Map<String, Object> hibernateProperties) {
    hibernateProperties.put("hibernate.session_factory.statement_inspector", this);
  }
}
