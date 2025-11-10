package com.devtalles.my_cv_spring.cv.controller;

import com.devtalles.my_cv_spring.cv.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/cv")
@RequiredArgsConstructor()
public class CVController {

  private final Person person;

  // http://localhost:8080/cv/home
  @GetMapping({"/home", "", "/"})
  public String index(Model model) {
  //    Person p = new Person("Goku", "Perez", "Saiyan");
  //    model.addAttribute("person", p);
    model.addAttribute("property", person.getFirst_name());
    return "index";
  }

  @GetMapping("/about")
  public String about() {
    return "about";
  }
}
