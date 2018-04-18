package pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gov.coi.cleanarchitecture.example.spring.pets.domain.model.entity.Pet;
import pl.gov.coi.cleanarchitecture.example.spring.pets.persistence.hibernate.entity.PetData;

import java.util.List;

/**
 * @author <a href="krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszyński</a>
 * @since 2018-04-12
 */
@Service
@RequiredArgsConstructor
final class PetMapperFacadeImpl implements PetMapperFacade {
  private final PetMapper petMapper;

  @Override
  public Pet map(PetData data) {
    return petMapper.map(data, new CyclicGraphContext());
  }

  @Override
  public List<PetData> map(List<Pet> pets) {
    return petMapper.map(pets, new CyclicGraphContext());
  }
}
