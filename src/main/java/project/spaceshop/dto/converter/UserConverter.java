package project.spaceshop.dto.converter;

import org.springframework.stereotype.Component;
import project.spaceshop.dto.UserDto;
import project.spaceshop.entity.User;

@Component
public class UserConverter {
    public User fromUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setUserSurname(user.getUserSurname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto fromUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setUserSurname(user.getUserSurname());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
