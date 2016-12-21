package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.petlist;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsResponse;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsResponseModel.Owner;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsResponseModel.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.RacePresenter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.petlist.PetListViewModel.PetViewModel;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.petlist.PetListViewModel.PetViewModel.PetViewModelBuilder;
import pl.gov.coi.cleanarchitecture.presentation.Presenter;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 16.12.16
 */
@RequiredArgsConstructor
class PetListPresenter implements Presenter<PetListView>, FetchPetsResponse {
  private static final String EMPTY_OWNER = "-";
  private PetListViewModel pets;
  private final RacePresenter racePresenter;

  @Override
  public PetListView createView() {
    PetListView view = new PetListView();
    view.setViewModel(pets);
    return view;
  }

  @Override
  public void setPets(Iterable<Pet> pets) {
    this.pets = createViewModel(pets);
  }

  private PetListViewModel createViewModel(Iterable<Pet> pets) {
    PetListViewModel viewModel = new PetListViewModel();
    StreamSupport
      .stream(pets.spliterator(), false)
      .forEach(pet -> processPet(pet, viewModel));
    return viewModel;
  }

  private void processPet(Pet pet, PetListViewModel viewModel) {
    @Nullable Owner owner = pet.getOwner();
    Optional<Owner> optionalOwner = Optional.ofNullable(owner);
    PetViewModelBuilder builder = PetViewModel.builder()
      .name(pet.getName())
      .race(racePresenter.present(pet.getRace()))
      .hasOwner(optionalOwner.isPresent())
      .owner(getOwner(owner));
    viewModel.add(builder.build());
  }

  private String getOwner(@Nullable Owner owner) {
    return Optional.ofNullable(owner)
      .map(PetListPresenter::ownerAsString)
      .orElse(EMPTY_OWNER);
  }

  private static String ownerAsString(Owner owner) {
    return String.format(
      "%s %s",
      owner.getName(),
      owner.getSurname()
    );
  }
}
