package com.practice.spsecurityjwt.configuration.security;

import com.practice.spsecurityjwt.models.Role;
import com.practice.spsecurityjwt.models.UserEntity;
import com.practice.spsecurityjwt.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository repository;


    public CustomUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return new User(user.getUsername(),user.getPassword(),mapRoleToAuthorities(user.getRoles()));
    }
    private Collection<GrantedAuthority> mapRoleToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
