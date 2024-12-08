package com.security.jwt.infrastructure.in.web;

import com.security.jwt.application.port.in.service.UserService;
import com.security.jwt.domain.User;
import com.security.jwt.infrastructure.in.web.request.UserRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequest request) {
        userService.register(request.toUser());
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest user) {
        return userService.login(user.toUser());
    }

    @GetMapping
    public User userInfo(@RequestParam String username) {
        return userService.userInfo(username);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        userService.logout(token);
    }
}
