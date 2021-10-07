package project.spaceshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    User findUserByPhone(String phone);
}
