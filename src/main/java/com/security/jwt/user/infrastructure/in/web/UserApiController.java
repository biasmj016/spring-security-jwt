package com.security.jwt.user.infrastructure.in.web;

import com.security.jwt.user.application.port.in.service.UserService;
import com.security.jwt.user.domain.User;
import com.security.jwt.user.infrastructure.in.web.request.RegistUserRequest;
import com.security.jwt.user.infrastructure.in.web.request.LoginUserRequest;
import com.security.jwt.user.infrastructure.in.web.response.FindUserResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegistUserRequest request) {
        userService.register(request.toUser());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserRequest user) {
        return userService.login(user.toUser());
    }

    @GetMapping
    public FindUserResponse userInfo(@RequestParam String username) {
        return userService.userInfo(username).toFindUserResponse();
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        userService.logout(token);
    }
}
