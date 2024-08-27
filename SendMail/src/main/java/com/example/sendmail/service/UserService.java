package com.example.sendmail.service;

import com.example.sendmail.dto.RegisterDTO;
import com.example.sendmail.model.Otp;
import com.example.sendmail.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Boolean existsEmail(String email);
    RegisterDTO createUser(RegisterDTO registerDTO);
    User findUserByEmail(String email);
}
