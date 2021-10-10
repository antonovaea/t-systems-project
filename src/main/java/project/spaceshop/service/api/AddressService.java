package project.spaceshop.service.api;

import project.spaceshop.entity.UserAddress;

import java.util.List;

public interface AddressService {

    void saveAddress(UserAddress userAddress);

    void changeAddress(UserAddress userAddress);

    UserAddress findAddressById(int idAddress);


}
