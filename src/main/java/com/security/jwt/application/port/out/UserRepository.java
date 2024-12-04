package com.security.jwt.application.port.out;

import com.security.jwt.domain.User;
import com.security.jwt.infrastructure.out.repository.UserEntity;
import com.security.jwt.infrastructure.out.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

public interface UserRepository {
    User findByUsername(String username);
    int countByUsername(String username);
    User save(User user);

    @Repository
    class UserRepositoryImpl implements UserRepository {
        private final UserJpaRepository userJpaRepository;

        public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
            this.userJpaRepository = userJpaRepository;
        }

        @Override
        @Transactional(readOnly = true)
        public User findByUsername(String username) {
            return userJpaRepository.findByUsername(username)
                    .map(UserEntity::toDomain)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
        }

        @Override
        @Transactional(readOnly = true)
        public int countByUsername(String username) {
            return userJpaRepository.countByUsername(username);
        }

        @Override
        @Transactional
        public User save(User user) {
            return userJpaRepository.save(new UserEntity(user)).toDomain();
        }
    }
}