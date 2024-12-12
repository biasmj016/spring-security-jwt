package com.security.jwt.user.application.port.out;

import com.security.jwt.user.domain.User;
import com.security.jwt.user.infrastructure.out.repository.UserJpaRepository;
import com.security.jwt.user.application.port.out.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserJpaRepository jpaRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository.UserRepositoryImpl(jpaRepository);
    }

    @Test
    void findByUsername() {
        User user = new User("user1", "password1");
        userRepository.save(user);
        assertNotNull(userRepository.findByUsername(user.username()));
    }

    @Test
    void countByUsername() {
        User user = new User("user1", "password1");
        userRepository.save(user);
        assertEquals(1, userRepository.countByUsername(user.username()));
    }

    @Test
    void save() {
        User user = new User("user2", "password2");
        assertDoesNotThrow(() -> userRepository.save(user));
    }
}