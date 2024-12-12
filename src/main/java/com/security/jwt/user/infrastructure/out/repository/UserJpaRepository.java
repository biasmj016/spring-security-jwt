package com.security.jwt.user.infrastructure.out.repository;

import com.security.jwt.user.infrastructure.out.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    int countByUsername(String username);
}