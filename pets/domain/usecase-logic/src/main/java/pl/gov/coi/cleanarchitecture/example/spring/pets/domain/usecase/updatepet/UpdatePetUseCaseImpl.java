package pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.updatepet;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 11.05.18
 */
final class UpdatePetUseCaseImpl implements UpdatePetUseCase {
  @Override
  public Consumer<UpdatePetResponse> updatePet(UpdatePetRequest request) {
    return response -> {
      // TODO: implement this
      throw new UnsupportedOperationException("not yet implemented");
    };
  }

  @Override
  public void execute(UpdatePetRequest request, UpdatePetResponse response) {
    updatePet(request).accept(response);
  }
}
