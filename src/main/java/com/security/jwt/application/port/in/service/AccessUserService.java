package com.security.jwt.application.port.in.service;

import com.security.jwt.application.port.in.usecase.FindUser;
import com.security.jwt.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessUserService implements UserDetailsService {
    private final FindUser findUser;

    public AccessUserService(FindUser findUser) {
        this.findUser = findUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUser.findUser(username);
        return new org.springframework.security.core.userdetails.User(
                user.username(),
                user.password(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}