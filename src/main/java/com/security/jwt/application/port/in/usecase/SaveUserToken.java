package com.security.jwt.application.port.in.usecase;

import com.security.jwt.domain.UserToken;
import com.security.jwt.infrastructure.out.repository.RedisUserRepository;
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
