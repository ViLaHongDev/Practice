package com.example.security_jwt.service;

import com.example.security_jwt.dto.user.RegisterRequest;
import com.example.security_jwt.dto.user.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean checkIfUserExists(String username);
    UserDTO createUser(RegisterRequest registerRequest);
}
