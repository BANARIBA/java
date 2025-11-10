package com.devtalles.my_cv_spring.cv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data()
@AllArgsConstructor()
@NoArgsConstructor()
@Component()
public class Person {

  @Value("${person.first_name}")
  private String first_name;
  private String last_name;
  private String profession;
}
