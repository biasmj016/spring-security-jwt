package com.security.jwt.user.application.port.in.usecase;

import com.security.jwt.user.application.port.out.UserRepository;
import com.security.jwt.user.domain.User;
import org.springframework.stereotype.Component;

public interface UpdateUser {
    void updateLoginDate(String username);

    @Component
    class UpdateUserUseCase implements UpdateUser {
        private final UserRepository userRepository;

        public UpdateUserUseCase(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public void updateLoginDate(String username) {
            User user = userRepository.findByUsername(username);
            userRepository.update(user.updateLoginDate());
        }
    }
}
