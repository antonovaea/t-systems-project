package project.spaceshop.service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.spaceshop.entity.User;
import project.spaceshop.entity.enums.UserRoleEnum;
import project.spaceshop.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found");

        Set<GrantedAuthority> roles = new HashSet<>();

        if (user.getRole().equals(UserRoleEnum.ADMIN.name())) {
            roles.add(new SimpleGrantedAuthority(UserRoleEnum.ADMIN.name()));
        } else {
            roles.add(new SimpleGrantedAuthority(user.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
    }
}
