package com.devtalles.my_cv_spring.cv.controller;

import com.devtalles.my_cv_spring.cv.model.Skill;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
@RequestMapping("/cv")
public class SkillsController {
  
  private final List<Skill> skills = new ArrayList<Skill>();
  
  @ModelAttribute(name="skills2")
  public List<Skill> getSkills () {
    return this.skills;
  }

  // http://localhost:8080/cv/skills
//  @GetMapping("/skills")
//  public String showSkills (Model model) {
//    model.addAttribute("skills", this.skills);
//    return "skills";
//  }
  
  // Busca por termino y retorna las skills que tenga este nombre o lo contengan por medio del query Param
  // localhost:8080/cv/skills/search-by-term?searchBy=java
  @GetMapping("/skills")
  public String showSkills (@RequestParam(defaultValue = "", required = false) String searchBy, Model model) {
    List<Skill> filteredSkillsBySearch = new ArrayList<>();
    if (searchBy.trim().length() > 0) {
      filteredSkillsBySearch = this.skills.stream().filter(skill -> skill.getName().toLowerCase().contains(searchBy.toLowerCase())).toList();
    } else {
      filteredSkillsBySearch = this.skills;
    }
    model.addAttribute("skills", filteredSkillsBySearch);
    return "skills";
  }
  
  // localhost:8080/cv/skills/id/:skill_id
  @GetMapping("skills/id/{skill_id}")
  public String showSkillDetail (@PathVariable() int skill_id, Model model) {
    if (skill_id >= 0 && skill_id < this.skills.size()) {
      Skill s = this.skills.get(skill_id);
      model.addAttribute("skill", s);
      return "skill-details";
    }
    return "redirect:/cv/skills";
  }
  
  // localhost:8080/cv/skills/search-by/Java/Avanzado
  @GetMapping("skills/search-by/{name}/{level}")
  public String showSkillDetailByNameAndLevel (@PathVariable() String name, @PathVariable() String level, Model model) {
    List<Skill> filteredListByNameAndLevel = this.skills
      .stream()
      .filter(
        skill -> skill.getName().equalsIgnoreCase(name) && skill.getLevel().equalsIgnoreCase(level)
      )
      .toList();
    model.addAttribute("skills", filteredListByNameAndLevel);
    model.addAttribute("filteredMessage", "Filtro: " + name + " - " + level);
    return "skills";
  }
  
  // localhost:8080/cv/skills/search-by-name/Java
//  @GetMapping("skills/search-by-name/{name}")
//  public String showSkillDetailByName (@PathVariable() String name, Model model) {
//    List<Skill> filteredListByNameAndLevel = this.skills
//      .stream()
//      .filter(
//        skill -> skill.getName().equalsIgnoreCase(name)
//      )
//      .toList();
//    if (filteredListByNameAndLevel.isEmpty()) {
//      model.addAttribute("message", "No se encontraron resultados para " + name);
//      return "forward:/cv/skills";
//    }
//    model.addAttribute("skills", filteredListByNameAndLevel);
//    model.addAttribute("filteredMessage", "Filtro: " + name);
//    return "skills";
//  }
  
  // localhost:8080/cv/skills/search-by-name/Java
  @GetMapping("skills/search-by-name/{name}")
  public String showSkillDetailByName (@PathVariable() String name, RedirectAttributes redirectAttributes) {
    List<Skill> filteredListByNameAndLevel = this.skills
      .stream()
      .filter(
        skill -> skill.getName().equalsIgnoreCase(name)
      )
      .toList();
    if (filteredListByNameAndLevel.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "No se encontraron resultados para " + name);
      return "redirect:/cv/skills?searchBy="+name;
    }
    redirectAttributes.addFlashAttribute("filteredMessage", "Filtro: " + name);
    return "redirect:/cv/skills?searchBy="+name;
  }
  
  // http://localhost:8080/cv/new
  @GetMapping("/skills/new")
  public String skillsForm (Model model) {
    model.addAttribute("skill", new Skill());
    return "add-skill";
  }
  
  // http://localhost:8080/cv/add
  @PostMapping("/skills/add")
  public String addSkill (@ModelAttribute Skill skill) {
    this.skills.add(skill);
    return "redirect:/cv/skills";
  }
}
