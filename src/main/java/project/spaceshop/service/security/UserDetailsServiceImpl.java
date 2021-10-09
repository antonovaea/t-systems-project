package project.spaceshop.service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.spaceshop.entity.User;
import project.spaceshop.entity.enums.UserRoleEnum;
import project.spaceshop.service.api.UserService;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found");

        Set<GrantedAuthority> roles = new HashSet<>();

        if (user.getRole().equals(UserRoleEnum.ROLE_ADMIN.name())) {
            roles.add(new SimpleGrantedAuthority(UserRoleEnum.ROLE_ADMIN.name()));
        } else {
            roles.add(new SimpleGrantedAuthority(user.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
    }
}
