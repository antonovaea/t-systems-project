package project.spaceshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.spaceshop.entity.User;
import project.spaceshop.service.api.AddressService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.UserService;

@Controller
//@Secured({"USER"})
@RequestMapping(value = "/")
public class UserController extends CommonController {

    private static final String PAGE_ACCOUNT = "redirect:/home/account";
    private final UserService userService;
    private final OrderService orderService;
    private final AddressService addressService;

    public UserController(UserDetailsService userDetailsService, UserService userService, OrderService orderService, AddressService addressService) {
        super(userDetailsService);
        this.userService = userService;
        this.orderService = orderService;
        this.addressService = addressService;
    }

    @GetMapping(value = "/home/account")
    public String accountInfo(Model model) {
        model.addAttribute("user", userService.findUserFromSecurityContextHolder());
        return "account";
    }

    @GetMapping(value = "/home/account/password")
    public String changePasswordForm() {
        return "passwordManager";
    }

    @PostMapping(value = "/home/account/change_password")
    @PreAuthorize("hasAuthority('USER')")
    public String changePassword(@RequestParam(name = "oldPassword") String oldPassword, @RequestParam(name = "newPassword") String newPassword) {
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        userService.changePassword(oldPassword, newPassword);
        return "/success";
    }


}
