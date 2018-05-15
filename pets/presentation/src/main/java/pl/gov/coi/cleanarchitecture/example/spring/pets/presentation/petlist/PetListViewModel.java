package pl.gov.coi.cleanarchitecture.example.spring.pets.presentation.petlist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Getter
class PetListViewModel {
  @Setter
  private long numberOfElements = 0L;
  private final List<PetViewModel> list = new ArrayList<>();

  void add(PetViewModel petViewModel) {
    list.add(petViewModel);
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
