package com.wak.eatsmeet.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret}") String secret) {
        // Convert secret string to SecretKey
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    public String generateActiveToken(String username, String role) {
        try {
            return Jwts.builder()
                    .claim("role", role)           // custom claim
                    .subject(username)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 2))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRefreshToken(String username, String role) {
        try {
            return Jwts.builder()
                    .claim("userId", username)   // custom claim
                    .claim("role", role)           // custom claim
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 2))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
