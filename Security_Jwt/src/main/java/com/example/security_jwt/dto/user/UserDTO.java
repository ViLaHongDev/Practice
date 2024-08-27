package com.example.security_jwt.dto.user;

import com.example.security_jwt.model.Role;
import lombok.Data;

import java.util.List;
@Data
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private List<Role> roles;
}
