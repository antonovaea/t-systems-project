package project.spaceshop.service.impl;

import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.entity.User;
import project.spaceshop.entity.UserAddress;
import project.spaceshop.mq.RabbitMqSender;
import project.spaceshop.repository.UserAddressRepository;
import project.spaceshop.service.api.AddressService;
import project.spaceshop.service.api.UserService;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final UserService userService;

    private final UserAddressRepository userAddressRepository;

    @Autowired
    public AddressServiceImpl(UserService userService, UserAddressRepository userAddressRepository) {
        this.userService = userService;
        this.userAddressRepository = userAddressRepository;
    }


    @Override
    public void saveAddress(UserAddress userAddress) {
        try {
            User user = userService.findUserFromSecurityContextHolder();
            userAddress.setUser(user);
            user.getUserAddresses().add(userAddress);
            userService.saveUser(user);
            log.info("new address added");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("address didn't added");
        }
    }

    @Override
    public void deleteAddress(int idAddress){
        try {
            User user = userService.findUserFromSecurityContextHolder();
            user.getUserAddresses().remove(findAddressById(idAddress));
            userService.saveUser(user);
            log.info("address with id " + idAddress + " removed");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("address with id " + idAddress + " didn't removed");
        }
    }

    @Override
    public UserAddress findAddressById(int idAddress) {
        return userAddressRepository.getById(idAddress);
    }
}
