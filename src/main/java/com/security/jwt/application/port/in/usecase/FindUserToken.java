package com.security.jwt.application.port.in.usecase;

import com.security.jwt.domain.UserToken;
import com.security.jwt.infrastructure.out.repository.RedisUserRepository;
import org.springframework.stereotype.Component;

public interface FindUserToken {
    UserToken find(String username);

    @Component
    class FindUserTokenUseCase implements FindUserToken {
        private final RedisUserRepository userRepository;

        public FindUserTokenUseCase(RedisUserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserToken find(String username) {
            return userRepository.findByUsernameOrNull(username);
        }
    }
}
