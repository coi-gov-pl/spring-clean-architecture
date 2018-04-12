package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import org.mapstruct.Mapper;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 12.04.18
 */
@Mapper(componentModel = "jsr330")
public interface DateMapper {
  default Instant map(@NotNull Date date) {
    return date.toInstant();
  }

  default Date map(Instant instant) {
    return Date.from(instant);
  }
}
