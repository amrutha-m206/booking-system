package com.autoassess.project.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET =
            "mySecretKeymySecretKeymySecretKey12345";

    public String generateToken(String email){

        SecretKey key =
                Keys.hmacShaKeyFor(SECRET.getBytes());

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 86400000
                        )
                )
                .signWith(key)
                .compact();
    }
}