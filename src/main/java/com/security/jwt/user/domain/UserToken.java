package com.security.jwt.user.domain;

public record UserToken(String username, String token) {
    public boolean isTokenValid(String token) {
        if (token == null) return false;
        return this.token.equals(token);
    }
}