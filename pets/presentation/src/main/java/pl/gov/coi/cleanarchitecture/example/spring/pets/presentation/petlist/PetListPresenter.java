package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.petlist;

import lombok.RequiredArgsConstructor;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.contract.EntityReference;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsResponse;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsResponseModel.Owner;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.usecase.fetchpets.FetchPetsResponseModel.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.incubation.pagination.PageInfo;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.presenter.RacePresenter;
import pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.mapper.EntityReferenceMapper;
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

  private final RacePresenter racePresenter;
  private final EntityReferenceMapper entityReferenceMapper;

  private PetListViewModel pets;
  private PageInfo pageInfo;

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

  @Override
  public void setPageInfo(PageInfo pageInfo) {
    this.pageInfo = pageInfo;
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
      .owner(getOwner(owner))
      .id(safeIdentifierOf(pet));
    viewModel.add(builder.build());
  }

  private CharSequence safeIdentifierOf(Pet pet) {
    EntityReference ref = pet.getReference();
    return entityReferenceMapper.map(ref);
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
