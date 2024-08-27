package com.example.sendmail.service.impl;

import com.example.sendmail.convert.ConvertRegisterDTO;
import com.example.sendmail.dto.RegisterDTO;
import com.example.sendmail.exception.NotFoundUserException;
import com.example.sendmail.model.Role;
import com.example.sendmail.model.User;
import com.example.sendmail.repository.RoleRepository;
import com.example.sendmail.repository.UserRepository;
import com.example.sendmail.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    ConvertRegisterDTO convertRegisterDTO;
    @Override
    public Boolean existsEmail(String email) {

        boolean checkEmailExist = userRepository.existsEmail(email);

        if (!checkEmailExist){
            return false;
        }

        return true;
    }

    @Override
    public RegisterDTO createUser(RegisterDTO registerDTO) {

        User user = new User();

        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new NotFoundUserException("Not found role have name is USER"));
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);
        return convertRegisterDTO.toDTO(user);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("Could not user by email "+email));
        return user;
    }
}
