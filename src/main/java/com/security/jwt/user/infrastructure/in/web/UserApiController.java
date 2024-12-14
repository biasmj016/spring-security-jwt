package com.security.jwt.user.infrastructure.in.web;

import com.security.jwt.global.response.ApiResponse;
import com.security.jwt.user.application.port.in.service.UserService;
import com.security.jwt.user.infrastructure.in.web.request.LoginUserRequest;
import com.security.jwt.user.infrastructure.in.web.request.RegistUserRequest;
import com.security.jwt.user.infrastructure.in.web.response.FindUserResponse;
import com.security.jwt.user.infrastructure.in.web.response.LoginUserResponse;
import org.springframework.web.bind.annotation.*;

import static com.security.jwt.global.response.ApiResponse.success;

@RestController
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody RegistUserRequest request) {
        userService.register(request.toUser());
        return success();
    }

    @PostMapping("/login")
    public ApiResponse<LoginUserResponse> login(@RequestBody LoginUserRequest user) {
        return success(userService.login(user.toUser()).toLoginUserResponse());
    }

    @GetMapping
    public ApiResponse<FindUserResponse> userInfo(@RequestParam String username) {
        return success(userService.userInfo(username).toFindUserResponse());
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestHeader("Authorization") String token) {
        userService.logout(token);
        return success();
    }
}
