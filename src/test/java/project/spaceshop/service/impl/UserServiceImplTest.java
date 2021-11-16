package project.spaceshop.service.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.entity.User;
import project.spaceshop.entity.enums.UserRoleEnum;
import project.spaceshop.repository.UserRepository;
import org.mockito.runners.MockitoJUnitRunner;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Before
    public User initUser() {
        User user = new User();
        user.setId(1);
        user.setUserName("JHON");
        user.setUserSurname("SMITH");
        user.setEmail("java@mail.com");
        user.setPhone("89999999999");
        user.setUserDateOfBirth("01.01.01");
        user.setRole("USER");
        user.setPassword("123456");
        return user;
    }


    @Test
    void findUserByEmail() {
        User user = initUser();
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(user);
        User selectedUser = userRepository.findUserByEmail("java@mail.com");
        assertEquals(user, selectedUser);

    }

    @Test
    void findUserByNullEmail() {

        User result = userServiceImpl.findUserByEmail(null);
        assertNull(result);

    }

    @Test
    void findUserByNotSuitableEmail() {
        User result = userServiceImpl.findUserByEmail("");
        assertNull(result);

    }

    @Test
    void findUserById() {
        User user = initUser();
        when(userRepository.findUserById(user.getId())).thenReturn(user);
        User selectedUser = userRepository.findUserById(1);
        assertEquals(user, selectedUser);
    }

    @Test
    void createUser() {

        User user = initUser();
        String role = "USER";

        boolean isUserCreated = userServiceImpl.createUser(user, role);

        assertTrue(isUserCreated);
        assertNotNull(user.getEmail());
        assertNotNull(user.getUserName());
        assertNotNull(user.getUserSurname());
        assertNotNull(user.getUserDateOfBirth());
        assertEquals(1, user.getId());
        assertNotNull(user.getPhone());
        assertTrue(CoreMatchers.is(user.getRole()).matches(UserRoleEnum.USER.toString()));

    }

    @Test
    void saveUser() {
        User user = initUser();
        boolean result = userServiceImpl.saveUser(user);
        assertTrue(result);

    }

    @Test
    void saveNullUser() {
        boolean result = userServiceImpl.saveUser(null);
        assertFalse(result);
    }

    @Test
    void deleteUser() {
        userServiceImpl.deleteUser(1);
        verify(userRepository).delete(userRepository.getById(1));
    }
}