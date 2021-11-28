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

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    /**
     * Injected by Spring UserService bean.
     */
    private final UserService userService;

    /**
     * Injected by Spring UserRepository bean.
     */
    private final UserAddressRepository userAddressRepository;

    /**
     * Injected constructor.
     *
     * @param userService           that must be injected.
     * @param userAddressRepository that must be injected.
     */
    @Autowired
    public AddressServiceImpl(UserService userService, UserAddressRepository userAddressRepository) {
        this.userService = userService;
        this.userAddressRepository = userAddressRepository;
    }

    /**
     * Method adds address to database.
     *
     * @param userAddress that must be added.
     */
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

    /**
     * Method removes address from database.
     *
     * @param idAddress that must be removed.
     */
    @Override
    public void deleteAddress(int idAddress) {
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

    /**
     * Method looks for address by id.
     *
     * @param idAddress id of required address.
     * @return found address.
     */
    @Override
    public UserAddress findAddressById(int idAddress) {
        return userAddressRepository.getById(idAddress);
    }
}
