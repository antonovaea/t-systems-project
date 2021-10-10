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

    private final UserConverter userConverter;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null || email.isEmpty()) return null;
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserFromSecurityContextHolder() {
        return findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public boolean isEmailFree(String email) {
        if (email == null || email.isEmpty()) return false;
        return userRepository.findUserByEmail(email) == null;
    }

    @Override
    public boolean isPhoneFree(String phone) {
        if (phone == null) return false;
        return userRepository.findUserByPhone(phone) == null;
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        User user = findUserFromSecurityContextHolder();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }

    }

    @Override
    public void createUser(UserDto userDto, User user) {
        user.setUserName(userDto.getUserName());
        user.setUserSurname(user.getUserSurname());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
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
