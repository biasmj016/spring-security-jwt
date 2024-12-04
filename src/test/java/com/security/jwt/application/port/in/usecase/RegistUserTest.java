package com.security.jwt.application.port.in.usecase;

import com.security.jwt.application.port.out.UserRepository;
import com.security.jwt.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RegistUserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegistUser.RegistUserUseCase registUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void regist() {
        User user = new User("testUser", "testPassword");
        when(userRepository.countByUsername(user.username())).thenReturn(0);
        registUserUseCase.regist(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void regist_exist() {
        User user = new User("existingUser", "testPassword");
        when(userRepository.countByUsername(user.username())).thenReturn(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> registUserUseCase.regist(user));
        assertEquals("User Name already exists", exception.getMessage());
        verify(userRepository, never()).save(user);
    }
}