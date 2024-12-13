package com.security.jwt.security.config;

import com.security.jwt.security.config.JwtKeyConfiguration.JwtKey;
import com.security.jwt.user.domain.UserToken;
import com.security.jwt.user.infrastructure.out.repository.RedisUserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final JwtKey jwtKey;
    private final RedisUserRepository userRepository;

    public JwtTokenProvider(JwtKey jwtKey, RedisUserRepository userRepository) {
        this.jwtKey = jwtKey;
        this.userRepository = userRepository;
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
        UserToken userToken = userRepository.findByUsername(username);
        return userToken.isTokenValid(token) &&getUsername(token).equals(username);
    }
}