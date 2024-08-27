package com.example.sendmail.dto.response;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String typeToken = "Bearer ";
    public AuthResponseDTO(String accessToken){
        this.accessToken = accessToken;
    }
}
