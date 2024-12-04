package com.security.jwt.infrastructure.out.repository;

import com.security.jwt.config.TestRedisConfig;
import com.security.jwt.domain.UserToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringJUnitConfig(classes = {TestRedisConfig.class, RedisUserRepository.class})
class RedisUserRepositoryTest {

    @Container
    public static GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2.6")
            .withExposedPorts(6379);

    @Autowired
    private RedisUserRepository redisUserRepository;

    @BeforeEach
    void setUp() {
        redisContainer.start();
    }

    @Test
    void testFindByUsernameOrNull_UserExists() {
        String username = "testUser";
        String token = "testToken";
        redisUserRepository.save(new UserToken(username, token));

        UserToken userToken = redisUserRepository.findByUsernameOrNull(username);

        assertNotNull(userToken);
        assertEquals(username, userToken.username());
        assertEquals(token, userToken.token());
    }

    @Test
    void testFindByUsernameOrNull_UserNotFound() {
        String username = "nonExistentUser";

        assertThrows(NoSuchElementException.class, () -> redisUserRepository.findByUsernameOrNull(username));
    }

    @Test
    void testSave() {
        String username = "testUser";
        String token = "testToken";
        UserToken userToken = new UserToken(username, token);

        redisUserRepository.save(userToken);

        UserToken retrievedToken = redisUserRepository.findByUsernameOrNull(username);
        assertEquals(token, retrievedToken.token());
    }

    @Test
    void testDelete() {
        String username = "testUser";
        String token = "testToken";
        redisUserRepository.save(new UserToken(username, token));

        redisUserRepository.delete(username);

        assertThrows(NoSuchElementException.class, () -> redisUserRepository.findByUsernameOrNull(username));
    }
}