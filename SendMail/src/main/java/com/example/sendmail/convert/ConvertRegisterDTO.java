package com.example.sendmail.convert;

import com.example.sendmail.dto.RegisterDTO;
import com.example.sendmail.model.User;
import org.springframework.stereotype.Component;

@Component
public class ConvertRegisterDTO {

    public RegisterDTO toDTO(User user){
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail(user.getEmail());
        registerDTO.setPassword(user.getPassword());
        registerDTO.setRole(user.getRoles());
        return registerDTO;
    }
}
