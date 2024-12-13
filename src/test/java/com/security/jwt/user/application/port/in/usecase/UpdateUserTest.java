package com.security.jwt.user.application.port.in.usecase;

import com.security.jwt.user.application.port.out.UserRepository;
import com.security.jwt.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateUserTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUser.UpdateUserUseCase updateUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateLoginDate() {
        String username = "testUser";
        User user = mock(User.class);
        when(userRepository.findByUsername(username)).thenReturn(user);
        when(user.updateLoginDate()).thenReturn(user);

        updateUserUseCase.updateLoginDate(username);

        verify(userRepository).findByUsername(username);
        verify(userRepository).update(user);
        verify(user).updateLoginDate();
    }

    @Test
    void updateLoginDate_userNotFound() {
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenThrow(new NoSuchElementException("User not found"));

        assertThrows(NoSuchElementException.class, () -> updateUserUseCase.updateLoginDate(username));

        verify(userRepository).findByUsername(username);
        verify(userRepository, never()).update(any(User.class));
    }
}