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

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null || email.isEmpty()) return null;
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public User findUserFromSecurityContextHolder() {
        return findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

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

    @Override
    public boolean saveUser(User user) {
        try {
            if (user != null){
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

    @Override
    public void deleteUser(int id) {
        userRepository.delete(userRepository.getById(id));
    }
}
