package com.practice.spsecurityjwt.configuration.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {

    @Value("${JWT_EXPIREDATE}")
    private long JWT_EXPIREDATE;
    @Value("${JWT_SECRET}")
    private String JWT_SECRET;
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIREDATE);

        try{
            String token = JWT.create()
                    .withSubject(username)
                    .withIssuedAt(new Date())
                    .withExpiresAt(expireDate)
                    .withClaim("ROLE","USER")
                    .sign(Algorithm.HMAC256(JWT_SECRET));
            return token;
        }catch (Exception e){
            throw new RuntimeException("Error while generating JWT",e);
        }
    }

    public String getUsernameFromJWT(String token){
        try {
            //Giải mã token
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                    .build()
                    .verify(token);

            // Lấy username từ token
            return decodedJWT.getSubject();
        }catch (JWTDecodeException e){
            // Xử lý ngoại lệ nếu token không hợp lệ
            throw new RuntimeException("Failed to decode JWT", e);
        }
    }

    public boolean validateToken(String token){
        try{
            // Tạo đối tượng Algorithm với JWT_SECRET để xác thực token
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

            // Xác thực token
            JWT.require(algorithm)
                    .build()
                    .verify(JWT_SECRET);
            // Nếu xác thực thành công trả về true
            return true;
        }catch (JWTVerificationException e){
            // Xử lý ngoại lệ nếu token không hợp lệ
            System.out.println("Invalid JWT Token : "+e.getMessage());
        }catch (Exception e){
            // Xử lý ngoại lệ khác
            System.out.println("Error while validating JWT:"+e.getMessage());
        }
        // trả về false nếu token không hợp lệ
        return false;
    }
}
