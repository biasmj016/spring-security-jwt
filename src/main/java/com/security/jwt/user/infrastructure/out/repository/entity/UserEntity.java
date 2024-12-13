package com.security.jwt.user.infrastructure.out.repository.entity;

import com.security.jwt.user.domain.User;
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
    @Column(unique = true)
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

    public void updateUser(User user) {
        this.password = user.password();
        this.lastLogin = user.lastLogin();
    }
}