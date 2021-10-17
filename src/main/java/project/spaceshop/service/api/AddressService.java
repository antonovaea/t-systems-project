package project.spaceshop.service.api;

import project.spaceshop.entity.UserAddress;

import java.util.List;

public interface AddressService {

    void saveAddress(UserAddress userAddress);

    void deleteAddress(int idAddress);

    UserAddress findAddressById(int idAddress);


}
