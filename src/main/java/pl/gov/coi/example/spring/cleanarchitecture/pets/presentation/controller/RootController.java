package pl.gov.coi.example.spring.cleanarchitecture.pets.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:krzysztof.suszynski@coi.gov.pl">Krzysztof Suszynski</a>
 * @since 19.12.16
 */
@Controller
public class RootController {

  private static final String REDIRECT_PETS = "redirect:/pets";

  @RequestMapping("/")
  public String root() {
    return REDIRECT_PETS;
  }
}
