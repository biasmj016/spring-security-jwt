package com.security.jwt.user.application.port.in.usecase;

import com.security.jwt.user.infrastructure.out.repository.RedisUserRepository;
import org.springframework.stereotype.Component;

public interface DeleteUserToken {
    void delete(String username);

    @Component
    class DeleteUserTokenUseCase implements DeleteUserToken {
        private final RedisUserRepository userRepository;

        public DeleteUserTokenUseCase(RedisUserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public void delete(String username) {
            userRepository.delete(username);
        }
    }
}
