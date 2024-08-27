package com.example.sendmail.service;

import com.example.sendmail.model.Otp;
import org.springframework.stereotype.Service;

@Service
public interface OtpService {
    String generateOtp(String email);
//    String getEmailByOtp(String otp);
//    public boolean validateOtp(String email,String otp);
//    Otp getOtpObject(String otp);
}
