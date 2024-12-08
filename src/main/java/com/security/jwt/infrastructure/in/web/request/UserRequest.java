package com.security.jwt.infrastructure.in.web.request;

import com.security.jwt.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;

    public User toUser() {
        return new User(username, password);
    }
}
