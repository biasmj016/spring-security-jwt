package com.security.jwt.user.domain;

import com.security.jwt.user.infrastructure.in.web.response.LoginUserResponse;

public record UserToken(String username, String token) {
    public boolean isTokenValid(String token) {
        if (token == null) return false;
        return this.token.equals(token);
    }

    public LoginUserResponse toLoginUserResponse() {
        return new LoginUserResponse(username, token);
    }
}