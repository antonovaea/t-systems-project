package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.entity.User;
import project.spaceshop.service.api.UserService;

@Controller
//@Secured({"ROLE_ANONYMOUS"})
@RequestMapping(value = "/")
public class LoginController extends CommonController {
    private final UserService userService;
    @Autowired
    public LoginController(UserDetailsService userDetailsService, UserService userService) {
        super(userDetailsService);
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("userSurname", user.getUserSurname());
        model.addAttribute("userDateOfBirth", user.getUserDateOfBirth());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        return "registration";
    }

    @PostMapping(value = "/registration_process")
    public String processRegister(User user) {
        user.setRole("USER");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.saveUser(user);
        return "registration_process";
    }



}
