package com.joaopaulofg.coreservice.auth;

import com.joaopaulofg.coreservice.auth.dtos.AuthLoginRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthLoginResponse;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthRegisterResponse> register(@RequestBody AuthRegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }
}

