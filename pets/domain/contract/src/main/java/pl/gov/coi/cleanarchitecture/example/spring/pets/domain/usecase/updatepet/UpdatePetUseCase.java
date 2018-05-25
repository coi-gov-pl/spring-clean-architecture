package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet;

import pl.gov.coi.cleanarchitecture.usecase.UseCase;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
public interface UpdatePetUseCase extends UseCase<UpdatePetRequest, UpdatePetResponse> {
  Consumer<UpdatePetResponse> updatePet(UpdatePetRequest request);
}
