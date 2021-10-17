package project.spaceshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.entity.User;
import project.spaceshop.entity.UserAddress;
import project.spaceshop.repository.UserAddressRepository;
import project.spaceshop.service.api.AddressService;
import project.spaceshop.service.api.UserService;

@Service
public class AddressServiceImpl implements AddressService {

    private final UserService userService;

    private final UserAddressRepository userAddressRepository;

    @Autowired
    public AddressServiceImpl(UserService userService, UserAddressRepository userAddressRepository) {
        this.userService = userService;
        this.userAddressRepository = userAddressRepository;
    }


    @Override
    public void saveAddress(UserAddress userAddress) {
        User user = userService.findUserFromSecurityContextHolder();
        userAddress.setUser(user);
        user.getUserAddresses().add(userAddress);
        userService.saveUser(user);
    }

    @Override
    public void deleteAddress(int idAddress){
        User user = userService.findUserFromSecurityContextHolder();
        user.getUserAddresses().remove(findAddressById(idAddress));
        userService.saveUser(user);
    }

    @Override
    public UserAddress findAddressById(int idAddress) {
        UserAddress userAddress = userAddressRepository.getById(idAddress);
        return userAddress;
    }
}
