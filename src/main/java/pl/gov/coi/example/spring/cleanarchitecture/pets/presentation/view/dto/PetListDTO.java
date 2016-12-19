package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.view.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Getter
public class PetListDTO {
  private final List<PetDTO> list = new ArrayList<>();

  public PetListDTO add(PetDTO petDTO) {
    list.add(petDTO);
    return this;
  }

  public int getCount() {
    return list.size();
  }

  @Builder
  @Getter
  public static class PetDTO {
    private String race;
    private String name;
    private boolean hasOwner;
    private String owner;
    private boolean hasFormerOwners;
  }
}
