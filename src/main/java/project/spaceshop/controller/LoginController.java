package project.spaceshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @GetMapping("/login")
    public String get(Model model) {
        model.addAttribute("title", "Login form");
        return "login";
    }

}
