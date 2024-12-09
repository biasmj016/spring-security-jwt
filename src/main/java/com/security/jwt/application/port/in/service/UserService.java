package com.security.jwt.application.port.in.service;

import com.security.jwt.application.port.in.usecase.*;
import com.security.jwt.domain.User;
import com.security.jwt.domain.UserToken;
import com.security.jwt.infrastructure.config.security.JwtTokenProvider;
import com.security.jwt.infrastructure.config.security.PasswordEncryption;
import org.springframework.stereotype.Service;

public interface UserService {
    void register(User user);
    String login(User user);
    User userInfo(String username);
    void logout(String token);

    @Service
    class UserServiceImpl implements UserService {
        private final RegistUser registUser;
        private final FindUser findUser;
        private final SaveUserToken saveUserToken;
        private final DeleteUserToken deleteUserToken;
        private final PasswordEncryption passwordEncryption;
        private final JwtTokenProvider jwtTokenProvider;

        public UserServiceImpl(
                RegistUser registUser,
                FindUser findUser,
                SaveUserToken saveUserToken,
                DeleteUserToken deleteUserToken,
                PasswordEncryption passwordEncryption,
                JwtTokenProvider jwtTokenProvider) {
            this.registUser = registUser;
            this.findUser = findUser;
            this.saveUserToken = saveUserToken;
            this.deleteUserToken = deleteUserToken;
            this.passwordEncryption = passwordEncryption;
            this.jwtTokenProvider = jwtTokenProvider;
        }

        @Override
        public void register(User user) {
            String encryptedPassword = passwordEncryption.encrypt(user.password());
            User newUser = new User(user.username(), encryptedPassword);
            registUser.regist(newUser);
        }

        @Override
        public String login(User user) {
            User foundUser = findUser.findUser(user.username());

            if (!passwordEncryption.matches(user.password(), foundUser.password())) {
                throw new IllegalArgumentException("Invalid username or password");
            }

            String token = jwtTokenProvider.createToken(user.username());
            saveUserToken.save(new UserToken(user.username(), token));
            return token;
        }

        @Override
        public User userInfo(String username) {
            return findUser.findUser(username);
        }

        @Override
        public void logout(String token) {
            if (token != null && token.startsWith("Bearer ")) {
                String jwtToken = token.substring(7);
                String username = jwtTokenProvider.getUsername(jwtToken);
                deleteUserToken.delete(username);
            }
        }
    }
}
