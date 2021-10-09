package project.spaceshop.service.api;

import project.spaceshop.dto.UserDto;
import project.spaceshop.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    User findUserFromSecurityContextHolder();

    //Method checks is this email already exists, because it must be uniq

    boolean isEmailFree(String email);

    //Method checks is this phone already exists, because it must be uniq

    boolean isPhoneFree(String phone);

    void changePassword(String oldPassword, String newPassword);

    void createUser(UserDto userDto);

    void saveUser(User user);

    void deleteUser(int id);
}
