package com.example.security_jwt.dto.user;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
