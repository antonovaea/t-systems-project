package project.spaceshop.service.impl;

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
    public void changePassword(String oldPassword, String newPassword) {
        User user = findUserFromSecurityContextHolder();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void createUser(User user, String role) {
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public boolean saveUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(userRepository.getById(id));
    }
}
