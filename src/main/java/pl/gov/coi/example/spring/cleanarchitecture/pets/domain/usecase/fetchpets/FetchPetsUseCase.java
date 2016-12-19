package pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.fetchpets;

import pl.gov.coi.example.spring.cleanarchitecture.core.domain.usecase.UseCase;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
public interface FetchPetsUseCase extends UseCase<FetchPetsRequest, FetchPetsResponse> {
}
