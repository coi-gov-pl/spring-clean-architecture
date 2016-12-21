package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.gov.coi.cleanarchitecture.usecase.Request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@ToString
@Getter
@Builder
@RequiredArgsConstructor
public class FetchPetsRequest implements Request {
  @Min(1)
  @Max(100)
  private final int maxElements;
}
