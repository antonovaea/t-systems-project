package project.spaceshop.service.api;

import project.spaceshop.dto.UserDto;
import project.spaceshop.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    User findUserById(int id);

    User findUserFromSecurityContextHolder();

    boolean changePassword(String oldPassword, String newPassword);

    boolean createUser(User user, String role);

    boolean saveUser(User user);

    void deleteUser(int id);
}
