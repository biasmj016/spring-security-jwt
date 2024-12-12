package com.security.jwt.user.application.port.in.usecase;

import com.security.jwt.user.domain.UserToken;
import com.security.jwt.user.infrastructure.out.repository.RedisUserRepository;
import com.security.jwt.user.application.port.in.usecase.SaveUserToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SaveUserTokenTest {

    @Mock
    private RedisUserRepository userRepository;

    @InjectMocks
    private SaveUserToken.SaveUserTokenUseCase saveUserTokenUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        UserToken userToken = new UserToken("testUser", "testToken");
        saveUserTokenUseCase.save(userToken);
        verify(userRepository, times(1)).save(userToken);
    }
}