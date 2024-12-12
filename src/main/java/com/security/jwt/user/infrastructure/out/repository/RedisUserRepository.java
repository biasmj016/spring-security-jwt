package com.security.jwt.user.infrastructure.out.repository;

import com.security.jwt.user.domain.UserToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class RedisUserRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String KEY = "user";

    public RedisUserRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public UserToken findByUsernameOrNull(String username) {
        return Optional.ofNullable((String) redisTemplate.opsForHash().get(KEY, username))
                .map(token -> new UserToken(username, token))
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public void save(UserToken userToken) {
        redisTemplate.opsForHash().put(KEY, userToken.username(), userToken.token());
    }

    public void delete(String username) {
        redisTemplate.opsForHash().delete(KEY, username);
    }
}
