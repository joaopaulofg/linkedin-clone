package com.joaopaulofg.coreservice.auth.dtos;

public record AuthLoginRequest(
        String email,
        String password
){}
