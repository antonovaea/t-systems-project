package project.spaceshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "redirect:/home/catalog/page/1";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/account")
    public String account() {
        return "redirect:/home/account";
    }

}
