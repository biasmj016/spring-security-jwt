package com.security.jwt.user.infrastructure.config.security;

import com.security.jwt.security.config.JwtKeyConfiguration;
import com.security.jwt.security.config.SecurityProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = JwtKeyConfigurationTest.TestConfig.class)
class JwtKeyConfigurationTest {

    @Autowired
    private JwtKeyConfiguration jwtKeyConfiguration;
    private JwtKeyConfiguration.JwtKey jwtKey;

    private static final String SECRET = "2794f906cbd0d2f4568db2079444fe3433160c861d776a33b745d066f4309763";
    private static final String ISSUER = "issuer";
    private static final long EXPIRATION = 3600000;

    @BeforeEach
    void setUp() {
        jwtKey = jwtKeyConfiguration.jwtKey();
    }

    @Test
    void testJwtKey() {
        assertNotNull(jwtKey);
        assertEquals(SECRET, jwtKey.secret());
        assertEquals(ISSUER, jwtKey.issuer());
        assertNotNull(jwtKey.key());
        assertEquals(EXPIRATION, jwtKey.expiration());
    }

    @Configuration
    @EnableConfigurationProperties(SecurityProperties.class)
    static class TestConfig {

        @Bean
        @Primary
        public SecurityProperties securityProperties() {
            return new SecurityProperties(SECRET, EXPIRATION, ISSUER);
        }

        @Bean
        public JwtKeyConfiguration jwtKeyConfiguration(SecurityProperties properties) {
            return new JwtKeyConfiguration(properties);
        }
    }
}