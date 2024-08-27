package com.example.sendmail.dto;

import com.example.sendmail.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class RegisterDTO {
    private String email;
    private String password;
    private List<Role> role;
}
