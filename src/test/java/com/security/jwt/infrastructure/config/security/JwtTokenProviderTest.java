package com.security.jwt.infrastructure.config.security;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {
    private JwtTokenProvider jwtTokenProvider;
    private JwtKeyConfiguration.JwtKey jwtKey;

    @BeforeEach
    void setUp() {
        SecretKey secretKey = Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey".getBytes(StandardCharsets.UTF_8));
        jwtKey = new JwtKeyConfiguration.JwtKey("mysecretkey", "issuer", secretKey, 3600000);
        jwtTokenProvider = new JwtTokenProvider(jwtKey);
    }

    @Test
    void testGenerateToken() {
        String token = jwtTokenProvider.createToken("testuser");
        assertNotNull(token);
    }

    @Test
    void testExtractUsername() {
        String token = jwtTokenProvider.createToken("testuser");
        String username = jwtTokenProvider.getUsername(token);
        assertEquals("testuser", username);
    }

    @Test
    void testValidateToken() {
        String token = jwtTokenProvider.createToken("testuser");
        boolean isValid = jwtTokenProvider.validateToken(token, "testuser");
        assertTrue(isValid);
    }

    @Test
    void testValidateToken_InvalidUsername() {
        String token = jwtTokenProvider.createToken("testuser");
        boolean isValid = jwtTokenProvider.validateToken(token, "invaliduser");
        assertFalse(isValid);
    }
}