package com.security.jwt.user.domain;

import java.time.LocalDateTime;

public record User (
        Long id,
        String username,
        String password,
        LocalDateTime createdAt,
        LocalDateTime lastLogin
){
    public User(String username, String password) {
        this(null, username, password, null, null);
    }
}
