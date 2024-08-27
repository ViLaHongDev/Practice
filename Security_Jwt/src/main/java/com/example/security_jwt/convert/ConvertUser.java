package com.example.security_jwt.convert;

import com.example.security_jwt.dto.user.UserDTO;
import com.example.security_jwt.model.User;

public class ConvertUser {
    public static User toModel(UserDTO userResponse){
        User user = new User();
        user.setId(userResponse.getId());
        user.setUsername(userResponse.getUsername());
        user.setPassword(userResponse.getPassword());
        user.setRoles(userResponse.getRoles());
        return user;
    }
    public static UserDTO toResponse(User user){
        UserDTO userResponse = new UserDTO();
        userResponse.setId(user.getId());
        userResponse.setUsername(userResponse.getUsername());
        userResponse.setPassword(userResponse.getPassword());
        userResponse.setRoles(user.getRoles());
        return userResponse;
    }
}
