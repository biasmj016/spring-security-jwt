package com.security.jwt.user.application.port.in.usecase;

import com.security.jwt.user.application.port.out.UserRepository;
import com.security.jwt.user.domain.User;
import org.springframework.stereotype.Component;

public interface FindUser {
    User findUser(String username);

    @Component
    class FindUserUseCase implements FindUser {
        private final UserRepository userRepository;

        public FindUserUseCase(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public User findUser(String username) {
            return userRepository.findByUsername(username);
        }
    }
}
