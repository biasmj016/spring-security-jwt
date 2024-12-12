package com.security.jwt.user.application.port.in.usecase;

import com.security.jwt.user.application.port.out.UserRepository;
import com.security.jwt.user.domain.User;
import org.springframework.stereotype.Component;

public interface RegistUser {
    void regist(User user);

    @Component
    class RegistUserUseCase implements RegistUser {
        private final UserRepository userRepository;

        public RegistUserUseCase(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public void regist(User user) {
            if(userRepository.countByUsername(user.username()) > 0){
                throw new IllegalArgumentException("User Name already exists");
            }
            userRepository.save(user);
        }
    }
}
