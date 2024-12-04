package com.security.jwt.infrastructure.config.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtKeyConfiguration {
    private final SecurityProperties properties;

    public JwtKeyConfiguration(SecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public JwtKey jwtKey() {
        return new JwtKey(
                properties.secret(),
                properties.issuer(),
                Keys.hmacShaKeyFor(properties.secret().getBytes(StandardCharsets.UTF_8)),
                properties.expiration()
        );
    }

    public record JwtKey(
            String secret,
            String issuer,
            SecretKey key,
            long expiration
    ) {}
}