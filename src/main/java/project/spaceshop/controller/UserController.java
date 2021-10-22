package project.spaceshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.entity.User;
import project.spaceshop.entity.UserAddress;
import project.spaceshop.repository.UserAddressRepository;
import project.spaceshop.service.api.AddressService;
import project.spaceshop.service.api.OrderService;
import project.spaceshop.service.api.UserService;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/")
public class UserController extends CommonController {

    private final UserAddressRepository userAddressRepository;
    private final UserService userService;
    private final OrderService orderService;
    private final AddressService addressService;

    public UserController(UserDetailsService userDetailsService, UserAddressRepository userAddressRepository, UserService userService, OrderService orderService, AddressService addressService) {
        super(userDetailsService);
        this.userAddressRepository = userAddressRepository;
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
    public String allAddress(Model model){
        User user = userService.findUserFromSecurityContextHolder();
        List<UserAddress> userAddressList = user.getUserAddresses();
        model.addAttribute("user", user);
        model.addAttribute("addresses", userAddressList);
        return "userAddress";
    }


    @GetMapping(value = "/home/account/address/{userId}")
    public String addAddress(Model model, @PathVariable("userId") int id) {
        User user = userService.findUserFromSecurityContextHolder();
        List<UserAddress> userAddressList = userService.findUserById(id).getUserAddresses();
        for (UserAddress userAddress : userAddressList){
            model.addAttribute("user", user);
            model.addAttribute("address", userAddress);
            model.addAttribute("country", userAddress.getCountry());
            model.addAttribute("city", userAddress.getCity());
            model.addAttribute("postcode", userAddress.getPostcode());
            model.addAttribute("street", userAddress.getStreet());
            model.addAttribute("house", userAddress.getHouse());
            model.addAttribute("flat", userAddress.getFlat());
        }
        return "address_add";
    }

    @PostMapping(value = "/home/account/address/{id}")
    public String deleteAddress(@PathVariable("id") int id){
        addressService.deleteAddress(id);
        return "redirect:/home/account";
    }

    @PostMapping(value = "/home/account/address_process")
    public String processSavingAddress(UserAddress userAddress) {
        addressService.saveAddress(userAddress);
        return "address_process";
    }




}
