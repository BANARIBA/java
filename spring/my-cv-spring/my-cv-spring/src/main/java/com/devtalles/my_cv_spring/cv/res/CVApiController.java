package com.devtalles.my_cv_spring.cv.res;

import com.devtalles.my_cv_spring.cv.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/api/person
@RestController()
@RequestMapping("/api")
public class CVApiController {

  @GetMapping("/person")
  public Person getPerson() {
    return new Person("Bryan Ariel", "Bonito Bonito", "Ingerniero en Sistemas FS");
  }
}
