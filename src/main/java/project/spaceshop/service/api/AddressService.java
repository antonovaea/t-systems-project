package project.spaceshop.service.api;

import project.spaceshop.entity.UserAddress;

import java.util.List;

/**
 * Interface provides API we can use to manipulate user address.
 */
public interface AddressService {

    /**
     * Method adds address to database.
     *
     * @param userAddress that must be added.
     */
    void saveAddress(UserAddress userAddress);

    /**
     * Method removes address from database.
     *
     * @param idAddress that must be removed.
     */
    void deleteAddress(int idAddress);

    /**
     * Method looks for address by id.
     *
     * @param idAddress id of required address.
     * @return found address.
     */
    UserAddress findAddressById(int idAddress);


}
