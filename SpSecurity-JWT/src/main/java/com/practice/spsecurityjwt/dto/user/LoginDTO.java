package com.practice.spsecurityjwt.dto.user;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}
