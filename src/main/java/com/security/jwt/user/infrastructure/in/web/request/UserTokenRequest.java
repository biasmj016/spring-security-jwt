package com.security.jwt.user.infrastructure.in.web.request;

import com.security.jwt.user.domain.UserToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserTokenRequest {
    private String username;
    private String token;

    public UserToken toUserToken() {
        return new UserToken(username, token);
    }
}
