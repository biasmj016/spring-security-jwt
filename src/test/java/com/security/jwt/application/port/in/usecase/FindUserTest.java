package com.security.jwt.application.port.in.usecase;

import com.security.jwt.application.port.out.UserRepository;
import com.security.jwt.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindUserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindUser.FindUserUseCase findUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void find() {
        String username = "testUser";
        User user = new User(username, "testPassword");
        when(userRepository.findByUsername(username)).thenReturn(user);
        User foundUser = findUserUseCase.findUser(username);
        assertNotNull(foundUser);
        assertEquals(username, foundUser.username());
    }

    @Test
    void find_none() {
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenThrow(new NoSuchElementException("User not found"));
        assertThrows(NoSuchElementException.class, () -> findUserUseCase.findUser(username));
    }
}