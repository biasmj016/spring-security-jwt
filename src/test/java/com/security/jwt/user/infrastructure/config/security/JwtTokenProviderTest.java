package com.security.jwt.user.infrastructure.config.security;

import com.security.jwt.security.config.JwtKeyConfiguration;
import com.security.jwt.security.config.JwtTokenProvider;
import com.security.jwt.user.domain.UserToken;
import com.security.jwt.user.infrastructure.out.repository.RedisUserRepository;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtTokenProviderTest {
    private JwtTokenProvider jwtTokenProvider;
    private JwtKeyConfiguration.JwtKey jwtKey;
    private RedisUserRepository userRepository;

    @BeforeEach
    void setUp() {
        SecretKey secretKey = Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey".getBytes(StandardCharsets.UTF_8));
        jwtKey = new JwtKeyConfiguration.JwtKey("mysecretkey", "issuer", secretKey, 3600000);
        userRepository = Mockito.mock(RedisUserRepository.class);
        jwtTokenProvider = new JwtTokenProvider(jwtKey, userRepository);
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
        UserToken userToken = new UserToken("testuser", token);
        when(userRepository.findByUsername("testuser")).thenReturn(userToken);

        boolean isValid = jwtTokenProvider.validateToken(token, "testuser");
        assertTrue(isValid);
    }
}