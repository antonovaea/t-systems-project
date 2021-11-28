package project.spaceshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.UserDto;
import project.spaceshop.dto.converter.UserConverter;
import project.spaceshop.entity.User;
import project.spaceshop.repository.UserRepository;
import project.spaceshop.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Injected by spring UserRepository bean
     */
    private final UserRepository userRepository;

    /**
     * Injected by spring PasswordEncoder bean
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Injected constructor.
     *
     * @param userRepository  that must be injected.
     * @param passwordEncoder that must be injected.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method looks for user by email.
     *
     * @param email email of user that must be found.
     * @return user object.
     */
    @Override
    public User findUserByEmail(String email) {
        if (email == null || email.isEmpty()) return null;
        return userRepository.findUserByEmail(email);
    }

    /**
     * Method looks for user by id.
     *
     * @param id param id of user that must be found.
     * @return user object.
     */
    @Override
    public User findUserById(int id) {
        return userRepository.getById(id);
    }

    /**
     * User have their own SecurityContextHolder which is connected with session.
     * Method lets to get user from current SecurityContextHolder.
     *
     * @return user object {@link User}.
     */
    @Override
    public User findUserFromSecurityContextHolder() {
        return findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /**
     * Method changes user password.
     *
     * @param oldPassword user old password (current one).
     * @param newPassword new password that must be set up.
     * @return true if password successfully changed or false if not.
     */
    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        User user = findUserFromSecurityContextHolder();
        try {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            log.info("password for user " + user.getId() + " changed");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("password for user " + user.getId() + "has not changed");
            return false;
        }
    }

    /**
     * Method adds new user to database.
     *
     * @param user user object that must be added.
     * @param role user role that must be set up.
     * @return true if new user successfully added to database or false if not.
     */
    @Override
    public boolean createUser(User user, String role) {
        try {
            user.setRole(role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            log.info("new user created with id " + user.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("new user has not created");
            return false;
        }

    }

    /**
     * Method adds new user to database.
     *
     * @param user user user object that must be added.
     * @return true if new user successfully added to database or false if not.
     */
    @Override
    public boolean saveUser(User user) {
        try {
            if (user != null) {
                userRepository.save(user);
                log.info("user saved");
                return true;
            } else return false;

        } catch (Exception e) {
            e.printStackTrace();
            log.info("user has not saved");
            return false;
        }
    }

    /**
     * Method removes user from database by id.
     *
     * @param id param id of user that must be removed.
     */
    @Override
    public void deleteUser(int id) {
        userRepository.delete(userRepository.getById(id));
    }
}
