package com.security.jwt.user.infrastructure.in.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class FindUserResponse {
    private String username;
    private String createdAt;
    private String lastLogin;

    public FindUserResponse(String username, LocalDateTime createdAt, LocalDateTime lastLogin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.username = username;
        this.createdAt = toDateString(createdAt);
        this.lastLogin = toDateString(lastLogin);
    }

    private String toDateString(LocalDateTime date) {
        if (date == null ) return "-";
        return  date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
