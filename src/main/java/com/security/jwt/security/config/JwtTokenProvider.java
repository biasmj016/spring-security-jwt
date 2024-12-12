package com.security.jwt.security.config;

import com.security.jwt.security.config.JwtKeyConfiguration.JwtKey;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final JwtKey jwtKey;

    public JwtTokenProvider(JwtKey jwtKey) {
        this.jwtKey = jwtKey;
    }

    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer(jwtKey.issuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtKey.expiration()))
                .signWith(jwtKey.key())
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtKey.key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        return getUsername(token).equals(username);
    }
}