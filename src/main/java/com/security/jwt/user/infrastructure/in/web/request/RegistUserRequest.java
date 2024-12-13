package com.security.jwt.user.infrastructure.in.web.request;

import com.security.jwt.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistUserRequest {
    private String username;
    private String password;

    public User toUser() {
        return new User(username, password);
    }
}
