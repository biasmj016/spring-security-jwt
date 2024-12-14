package com.security.jwt.user.infrastructure.in.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserResponse {
    private String username;
    private String token;

    public LoginUserResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
