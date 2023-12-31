package ru.netology.cloudstoragealeks.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.cloudstoragealeks.repository.AuthenticationRepository;
import ru.netology.cloudstoragealeks.config.security.jwt.JwtTokenUtil;
import ru.netology.cloudstoragealeks.dto.response.AuthResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.netology.cloudstoragealeks.DataTest.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("=== Testing authentication service ===")

public class AuthenticationServiceTest {
    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    AuthenticationRepository authenticationRepository;

    @Mock
    UserService userService;

    @Mock
    JwtTokenUtil jwtTokenUtil;

    @Test
    void loginTest() {
        when(userService.loadUserByUsername(USERNAME_1)).thenReturn(USER_DETAILS);
        when(jwtTokenUtil.generateToken(USER_DETAILS)).thenReturn(TOKEN_1);

        AuthResponse expected = RESPONSE;
        AuthResponse result = authenticationService.login(REQUEST);
        assertEquals(expected, result);
        Mockito.verify(authenticationRepository, Mockito.times(1)).putTokenAndUsername(TOKEN_1, USERNAME_1);
    }

    @Test
    void logoutTest() {
        when(authenticationRepository.getUsernameByToken(BEARER_TOKEN.substring(7))).thenReturn(USERNAME_1);
        authenticationService.logout(BEARER_TOKEN);
        Mockito.verify(authenticationRepository, Mockito.times(1)).getUsernameByToken(BEARER_TOKEN.substring(7));
        Mockito.verify(authenticationRepository, Mockito.times(1)).removeTokenAndUsernameByToken(BEARER_TOKEN.substring(7));
    }
}
