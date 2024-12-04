package com.security.jwt.application.port.in.usecase;

import com.security.jwt.infrastructure.out.repository.RedisUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteUserTokenTest {

    @Mock
    private RedisUserRepository userRepository;

    @InjectMocks
    private DeleteUserToken.DeleteUserTokenUseCase deleteUserTokenUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void delete() {
        String username = "testUser";
        deleteUserTokenUseCase.delete(username);
        verify(userRepository, times(1)).delete(username);
    }
}