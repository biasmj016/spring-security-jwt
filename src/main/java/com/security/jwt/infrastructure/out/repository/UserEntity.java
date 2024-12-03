package com.security.jwt.infrastructure.out.repository;

import com.security.jwt.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;

    public UserEntity(User user) {
        this.username = user.username();
        this.password = user.password();
        this.createdAt = LocalDateTime.now();
    }

    public User toDomain() {
        return new User(
                this.id,
                this.username,
                this.password,
                this.createdAt,
                this.lastLogin
        );
    }
}