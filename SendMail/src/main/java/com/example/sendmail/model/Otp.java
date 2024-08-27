package com.example.sendmail.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "otp")
@AllArgsConstructor
@NoArgsConstructor
public class Otp {
    @Id
    private String otp;
    private String email;
    private String status;
    private LocalDateTime expirationTime;
}
