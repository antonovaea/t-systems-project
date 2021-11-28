package project.spaceshop.service.api;

import project.spaceshop.dto.UserDto;
import project.spaceshop.entity.User;

/**
 * Interface provides API we can use to manipulate users.
 */
public interface UserService {

    /**
     * Method looks for user by email.
     *
     * @param email email of user that must be found.
     * @return user object.
     */
    User findUserByEmail(String email);

    /**
     * Method looks for user by id.
     *
     * @param id param id of user that must be found.
     * @return user object.
     */
    User findUserById(int id);

    /**
     * User have their own SecurityContextHolder which is connected with session.
     * Method lets to get user from current SecurityContextHolder.
     *
     * @return user object {@link User}.
     */
    User findUserFromSecurityContextHolder();

    /**
     * Method changes user password.
     *
     * @param oldPassword user old password (current one).
     * @param newPassword new password that must be set up.
     * @return true if password successfully changed or false if not.
     */
    boolean changePassword(String oldPassword, String newPassword);

    /**
     * Method adds new user to database.
     *
     * @param user user object that must be added.
     * @param role user role that must be set up.
     * @return true if new user successfully added to database or false if not.
     */
    boolean createUser(User user, String role);

    /**
     * Method adds new user to database.
     *
     * @param user user user object that must be added.
     * @return true if new user successfully added to database or false if not.
     */
    boolean saveUser(User user);

    /**
     * Method removes user from database by id.
     *
     * @param id param id of user that must be removed.
     */
    void deleteUser(int id);
}
