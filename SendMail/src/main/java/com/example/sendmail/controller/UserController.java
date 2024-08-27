package com.example.sendmail.controller;

import com.example.sendmail.configuration.jwt.JwtGenerate;
import com.example.sendmail.dto.ChangePassword;
import com.example.sendmail.dto.LoginDTO;
import com.example.sendmail.dto.RegisterDTO;
import com.example.sendmail.dto.response.AuthResponseDTO;
import com.example.sendmail.model.Otp;
import com.example.sendmail.model.User;
import com.example.sendmail.repository.UserRepository;
import com.example.sendmail.service.EmailService;
import com.example.sendmail.service.OtpService;
import com.example.sendmail.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerate jwtGenerate;
    private final OtpService otpService;
    private final EmailService emailService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){

        // Check mail already exists email in the database
        boolean emailExists = userService.existsEmail(registerDTO.getEmail());

        // If email exists send message This email already exist
        if (emailExists == false){
            return new ResponseEntity<>("This email already exist!",HttpStatus.BAD_REQUEST);
        }
        // If email not exists generate user
        userService.createUser(registerDTO);

        // Response Register Success , status OK
        return new ResponseEntity<>("Register Success",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerate.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@PathVariable("email") String email){
        // Kiểm tra email
        boolean checkEmailExist = userService.existsEmail(email);
        if (!checkEmailExist){
            return new ResponseEntity<>("Fail",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
