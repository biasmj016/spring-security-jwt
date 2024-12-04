package com.security.jwt.application.port.in.usecase;

import com.security.jwt.domain.UserToken;
import com.security.jwt.infrastructure.out.repository.RedisUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FindUserTokenTest {

    @Mock
    private RedisUserRepository userRepository;

    @InjectMocks
    private FindUserToken.FindUserTokenUseCase findUserTokenUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void find() {
        String username = "testUser";
        UserToken userToken = new UserToken(username, "testToken");
        when(userRepository.findByUsernameOrNull(username)).thenReturn(userToken);
        UserToken foundUserToken = findUserTokenUseCase.find(username);
        assertNotNull(foundUserToken);
        assertEquals(username, foundUserToken.username());
        assertEquals("testToken", foundUserToken.token());
    }

    @Test
    void find_none() {
        String username = "nonExistentUser";
        when(userRepository.findByUsernameOrNull(username)).thenThrow(new NoSuchElementException("User not found"));
        assertThrows(NoSuchElementException.class, () -> findUserTokenUseCase.find(username));

    }
}