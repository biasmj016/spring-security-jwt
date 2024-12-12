package com.security.jwt.user.application.port.in.usecase;

import com.security.jwt.user.domain.UserToken;
import com.security.jwt.user.infrastructure.out.repository.RedisUserRepository;
import org.springframework.stereotype.Component;

public interface SaveUserToken {
    void save(UserToken userToken);

    @Component
    class SaveUserTokenUseCase implements SaveUserToken {
        private final RedisUserRepository userRepository;

        public SaveUserTokenUseCase(RedisUserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public void save(UserToken userToken) {
            userRepository.save(userToken);
        }
    }
}