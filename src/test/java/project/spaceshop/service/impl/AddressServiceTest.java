package project.spaceshop.service.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.entity.User;
import project.spaceshop.entity.UserAddress;
import project.spaceshop.repository.UserAddressRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AddressServiceTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private UserAddressRepository userAddressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Before
    public User initUser() {
        User user = new User();
        user.setId(1);
        user.setUserName("JHON");
        user.setUserSurname("SMITH");
        user.setEmail("java@mail.com");
        user.setPhone("89999999999");
        user.setUserDateOfBirth("01.01.01");
        user.setRole("USER");
        user.setPassword("123456");
        return user;
    }

    @Before
    public UserAddress initUserAddress() {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(1);
        userAddress.setCountry("Russia");
        userAddress.setCity("Moscow");
        userAddress.setPostcode("618000");
        userAddress.setStreet("Street");
        userAddress.setHouse("20");
        userAddress.setFlat("1");
        return userAddress;
    }

    @Test
    void saveAddress() {
        User user = initUser();
        UserAddress userAddress = initUserAddress();
        List<UserAddress> addresses = new ArrayList<>();
        addresses.add(userAddress);
        when(userService.findUserFromSecurityContextHolder()).thenReturn(user);
        addressService.saveAddress(userAddress);
        assertEquals(1, user.getUserAddresses().size());

    }

    @Test
    void deleteAddress() {
        User user = initUser();
        UserAddress userAddress = initUserAddress();
        UserAddress userAddress1 = initUserAddress();
        List<UserAddress> addresses = new ArrayList<>();
        addresses.add(userAddress);
        addresses.add(userAddress1);
        when(userService.findUserFromSecurityContextHolder()).thenReturn(user);
        user.setUserAddresses(addresses);
        userService.saveUser(user);
        user.getUserAddresses().remove(userAddress1);
        userService.saveUser(user);
        assertEquals(1, user.getUserAddresses().size());
    }

    @Test
    void findAddressById() {
        UserAddress userAddress = initUserAddress();
        when(userAddressRepository.getById(userAddress.getId())).thenReturn(userAddress);
        UserAddress selectedAddress = addressService.findAddressById(1);
        assertEquals(userAddress, selectedAddress);
    }
}