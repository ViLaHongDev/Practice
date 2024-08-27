package com.example.security_jwt.controller;

import com.example.security_jwt.dto.user.RegisterRequest;
import com.example.security_jwt.dto.user.UserDTO;
import com.example.security_jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
         if (userService.checkIfUserExists(registerRequest.getUsername())){
                return new ResponseEntity<>("User exist", HttpStatus.BAD_REQUEST);
         }
         UserDTO userDTO = userService.createUser(registerRequest);
         return new ResponseEntity<>("Register Success",HttpStatus.OK);
    }
}
