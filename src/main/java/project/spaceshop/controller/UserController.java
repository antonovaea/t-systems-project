package project.spaceshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.entity.User;
import project.spaceshop.entity.UserAddress;
import project.spaceshop.service.api.AddressService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.UserService;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/")
public class UserController extends CommonController {

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
    public String changePassword(@RequestParam(name = "oldPassword") String oldPassword, @RequestParam(name = "newPassword") String newPassword) {
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        userService.changePassword(oldPassword, newPassword);
        return "/success";
    }

    @GetMapping(value = "/home/account/address")
    public String address(){
        return "redirect:/home/account/address_add";
    }

    @GetMapping(value = "/home/account/address_add")
    public String getAddress(Model model){
        UserAddress userAddress = new UserAddress();
        model.addAttribute("nameUser", userService.findUserFromSecurityContextHolder().getUserName());
        model.addAttribute("address", userAddress);
        model.addAttribute("country", userAddress.getCountry());
        model.addAttribute("city", userAddress.getCity());
        model.addAttribute("postcode", userAddress.getPostcode());
        model.addAttribute("street", userAddress.getStreet());
        model.addAttribute("house", userAddress.getHouse());
        model.addAttribute("flat", userAddress.getFlat());
        return "address_add";
    }

    @PostMapping(value = "/address_process")
    public String processSavingAddress(UserAddress userAddress) {
        User user = userService.findUserFromSecurityContextHolder();
        user.setUserAddress(userAddress);
        addressService.saveAddress(userAddress);
        userService.saveUser(user);
        return "address_process";
    }

    @PostMapping(value = "home/account/address/{id}")
    public String addAddress(Model model, @PathVariable("id") int id){
        if (addressService.findAddressById(userService.findUserById(id).getUserAddress().getId()) != null){
            return "userAddress";
        } else return "address_add";

    }





}
