package com.security.jwt.infrastructure.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record SecurityProperties(
        String secret,
        long expiration,
        String issuer
) {}