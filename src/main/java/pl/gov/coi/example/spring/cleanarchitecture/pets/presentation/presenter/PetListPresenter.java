package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.presenter;

import pl.gov.coi.example.spring.cleanarchitecture.core.presentation.Presenter;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Ownership;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.entity.Pet;
import pl.gov.coi.example.spring.cleanarchitecture.pets.domain.usecase.fetchpets.FetchPetsResponse;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.PetListView;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto.PetListDTO;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto.PetListDTO.PetDTO;
import pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto.PetListDTO.PetDTO.PetDTOBuilder;

import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
public class PetListPresenter implements Presenter<PetListView>, FetchPetsResponse {
  private PetListDTO pets;

  @Override
  public PetListView createView() {
    PetListView view = new PetListView();
    view.setViewModel(pets);
    return view;
  }

  @Override
  public void setPets(Iterable<Pet> pets) {
    this.pets = createDTO(pets);
  }

  private PetListDTO createDTO(Iterable<Pet> pets) {
    PetListDTO dto = new PetListDTO();
    StreamSupport
      .stream(pets.spliterator(), false)
      .forEach(pet -> {
        Optional<Ownership> optionalOwner = Optional.ofNullable(pet.getOwnership());
        PetDTOBuilder builder = PetDTO.builder()
          .name(pet.getName())
          .race(pet.getRace().getName())
          .hasOwner(optionalOwner.isPresent());
        optionalOwner.ifPresent(
          owner -> builder.owner(
            owner.getPerson().getName() + " " + owner.getPerson().getSurname()
          )
        );
        dto.add(builder.build());
      });
    return dto;
  }
}
