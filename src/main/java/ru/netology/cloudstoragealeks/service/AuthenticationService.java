package ru.netology.cloudstoragealeks.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.netology.cloudstoragealeks.repository.AuthenticationRepository;
import ru.netology.cloudstoragealeks.security.JwtTokenUtil;
import ru.netology.cloudstoragealeks.web.request.AuthRequest;
import ru.netology.cloudstoragealeks.web.response.AuthResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationRepository authenticationRepository;

    public AuthResponse login(AuthRequest request) {
        final String username = request.getLogin();
        final UserDetails userDetails = userService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        authenticationRepository.putTokenAndUsername(token, username);
        log.info("User {} is authorized", username);
        return AuthResponse.builder()
                .authToken(token)
                .build();
    }

    public void logout(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        final String username = authenticationRepository.getUsernameByToken(authToken);
        log.info("User {} logout", username);
        authenticationRepository.removeTokenAndUsernameByToken(authToken);
    }
}
