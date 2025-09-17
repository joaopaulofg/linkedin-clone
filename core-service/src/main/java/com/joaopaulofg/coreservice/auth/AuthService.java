package com.joaopaulofg.coreservice.auth;

import com.joaopaulofg.coreservice.auth.dtos.AuthLoginRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterResponse;
import com.joaopaulofg.coreservice.infra.security.JwtUtil;
import com.joaopaulofg.coreservice.user.User;
import com.joaopaulofg.coreservice.user.UserRepository;
import com.joaopaulofg.coreservice.user.UserService;
import com.joaopaulofg.coreservice.user.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthMapper authMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil  jwtUtil;

    public AuthRegisterResponse register(AuthRegisterRequest request) {
        return authMapper.toAuthRegisterResponse(userService.register(authMapper.toUser(request)));
    }

    public String login(AuthLoginRequest request) {
        User user = authenticate(request.email(), request.password());

        return jwtUtil.generateToken(user.getEmail(), user.getId());
    }

    private User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        if(!validatePassword(password, user.getPassword())) {
            throw new UserNotFoundException("Invalid email or password");
        }

        return user;
    }

    private boolean validatePassword(String plainPassword, String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }
}
