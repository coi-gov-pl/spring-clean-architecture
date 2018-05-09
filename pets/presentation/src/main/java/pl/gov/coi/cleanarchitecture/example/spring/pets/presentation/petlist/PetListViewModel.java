package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.petlist;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Getter
class PetListViewModel {
  private final List<PetViewModel> list = new ArrayList<>();

  void add(PetViewModel petViewModel) {
    list.add(petViewModel);
  }

  int getCount() {
    return list.size();
  }

  @Builder
  @Getter
  static class PetViewModel {
    private CharSequence id;
    private String race;
    private String name;
    private boolean hasOwner;
    private String owner;
    private boolean hasFormerOwners;
  }
}
