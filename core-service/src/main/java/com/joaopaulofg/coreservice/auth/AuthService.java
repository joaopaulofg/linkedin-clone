package com.joaopaulofg.coreservice.auth;

import com.joaopaulofg.coreservice.auth.dtos.AuthLoginRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthLoginResponse;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterResponse;
import com.joaopaulofg.coreservice.infra.security.JwtProvider;
import com.joaopaulofg.coreservice.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final AuthMapper authMapper;

    public AuthRegisterResponse register(AuthRegisterRequest request) {
        return authMapper.toAuthRegisterResponse(userService.register(authMapper.toUser(request)));
    }

    public AuthLoginResponse login(AuthLoginRequest request) {
        if(!userService.login(request.email(), request.password())) {
            throw new InvalidCredentialsException("Credenciais para login invalidas!");
        }
        String token = jwtProvider.generateToken(request.email());
        return new AuthLoginResponse(token);
    }
}
