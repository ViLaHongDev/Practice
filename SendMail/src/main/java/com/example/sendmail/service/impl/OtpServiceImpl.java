package com.example.sendmail.service.impl;

import com.example.sendmail.model.Otp;
import com.example.sendmail.repository.OtpRepository;
import com.example.sendmail.service.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {

//    private final RedisTemplate<String,Otp> redisTemplate;
    private final OtpRepository otpRepository;

    @Override
    public String generateOtp(String email) {

        String otpCode = String.format("%06d", new Random().nextInt(999999));

        Otp otpObj = new Otp();
        otpObj.setOtp(otpCode);
        otpObj.setEmail(email);
        otpObj.setStatus("Unverified");
        otpObj.setExpirationTime(LocalDateTime.now().plusMinutes(1));
        // Lưu OTP với TTL là 5 phút (300 giây)
//        redisTemplate.opsForValue().set(otpCode,otpObj,5,TimeUnit.MINUTES);
        otpRepository.save(otpObj);

        return otpCode;
    }

//    @Override
//    public String getEmailByOtp(String otp) {
//        Otp otpObj = (Otp) redisTemplate.opsForValue().get(otp);
//        return otpObj != null ? otpObj.getEmail() : null;
//    }
//
//    @Override
//    public boolean validateOtp(String email, String otp) {
//        Otp otpObj = (Otp) redisTemplate.opsForValue().get(otp);
//
//        if (otpObj != null && otpObj.getEmail().equals(email)){
//            // Cập nhật trạng thái OTP thành "Verified"
//            otpObj.setStatus("Verified");
//            redisTemplate.opsForValue().set(otp, otpObj, 5, TimeUnit.MINUTES);
//            return true;
//        }
//        return false;
//    }

//    @Override
//    public Otp getOtpObject(String otp) {
//        return redisTemplate.opsForValue().get(otp);
//    }
//

}
