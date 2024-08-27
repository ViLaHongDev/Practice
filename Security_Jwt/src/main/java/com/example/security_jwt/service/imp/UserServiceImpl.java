package com.example.security_jwt.service.imp;

import com.example.security_jwt.convert.ConvertUser;
import com.example.security_jwt.dto.user.RegisterRequest;
import com.example.security_jwt.dto.user.UserDTO;
import com.example.security_jwt.model.Role;
import com.example.security_jwt.model.User;
import com.example.security_jwt.repository.RoleRepository;
import com.example.security_jwt.repository.UserRepository;
import com.example.security_jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public boolean checkIfUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDTO createUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        Role role = roleRepository.findByName("USER");
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return ConvertUser.toResponse(user);
    }

}
