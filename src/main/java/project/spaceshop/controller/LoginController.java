package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.dto.UserDto;
import project.spaceshop.entity.User;
import project.spaceshop.service.api.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Secured({"ROLE_ANONYMOUS","ROLE_ADMIN"})
@RequestMapping(value = "/")
public class LoginController extends CommonController {
    private final UserService userService;
    private static final String PAGE_REGISTRATION = "userForm";
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
        model.addAttribute("userDto", new UserDto());
        return "registration";
    }


    @PostMapping(value = "/registration/email")
    @ResponseBody
    public boolean isEmailFree(@RequestParam(name = "email") String email) {
        return userService.isEmailFree(email);
    }

    @PostMapping(value = "/registration/phone")
    @ResponseBody
    public boolean isPhoneFree(@RequestParam(name = "phone") String phone) {
        return userService.isPhoneFree(phone);
    }


    @PostMapping(value = "/registration")
    public String signUp(@Valid UserDto userDto, BindingResult result, User user,
                         final HttpServletRequest request) {
        if (result.hasErrors()) {
            return PAGE_REGISTRATION;
        } else {
            if (!userService.isEmailFree(user.getEmail())) return PAGE_REGISTRATION;
            userService.createUser(userDto, user);
            authenticateUserAndSetSession(user.getEmail(), request);
        }
        return "redirect:/account/user";
    }

}
