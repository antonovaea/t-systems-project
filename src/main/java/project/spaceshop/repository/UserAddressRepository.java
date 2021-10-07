package project.spaceshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
}
