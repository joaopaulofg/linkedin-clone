package com.joaopaulofg.coreservice.auth;

import com.joaopaulofg.coreservice.auth.dtos.AuthLoginRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest request) {
        try{
            String token = authService.login(request);
            ResponseCookie cookie = ResponseCookie.from("token", token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(60 * 60)
                    .sameSite("Strict")
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(Map.of("message", "Login successful! Token: " + token));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}

