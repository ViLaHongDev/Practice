package com.example.sendmail.dto;

import lombok.Data;

@Data
public class ChangePassword {
    private String otpCode;
    private String passwordNew;
    private String passwordConfirm;
}
