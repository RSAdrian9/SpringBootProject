package com.Proyect.SpringBoot.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ControllerHotel {

    @GetMapping("/")
    public String greeting() {

        return "greeting";
    }

    @GetMapping("/greeting")
    public String greetingNormal (Model model) {
        model.addAttribute("name", "Hola Mundo");
        return "greeting";
    }

    @GetMapping("/greeting/{name}")
    public String greetingName (@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
