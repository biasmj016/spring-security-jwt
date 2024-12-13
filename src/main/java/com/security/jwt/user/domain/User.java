package com.security.jwt.user.domain;

import com.security.jwt.user.infrastructure.in.web.response.FindUserResponse;

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

    public User updateLoginDate() {
        return new User(id, username, password, createdAt, LocalDateTime.now());
    }

    public FindUserResponse toFindUserResponse() {
        return new FindUserResponse(username, createdAt, lastLogin);
    }
}
