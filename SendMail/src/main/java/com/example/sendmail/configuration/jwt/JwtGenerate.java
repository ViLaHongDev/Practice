package com.example.sendmail.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtGenerate {
    @Value("${JWT_EXPIREDATE}")
    private Long JWT_EXPIREDATE;
    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    public String generateToken(Authentication authentication){

        String email = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIREDATE);

        try {
            String token = JWT.create()
                    .withSubject(email)
                    .withIssuedAt(new Date())
                    .withExpiresAt(expireDate)
                    .withClaim("ROLE","USER")
                    .sign(Algorithm.HMAC256(JWT_SECRET));
            return token;
        }catch (Exception e){
            throw new RuntimeException("Error while generate JWT",e);
        }
    }


}
