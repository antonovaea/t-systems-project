package project.spaceshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public User findUserByEmail(String email)
    {
        if (email == null || email.isEmpty()) return null;
        return userRepository.findUserByEmail(email);
    }

//    @Override
//    public User findUserFromSecurityContextHolder() {
//        return findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
//    }

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
    public void changePassword(User user, String newPassword) {
//        User user = findUserFromSecurityContextHolder();
        user.setPassword(newPassword);
        userRepository.save(user);

    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(userRepository.getById(id));
    }
}
