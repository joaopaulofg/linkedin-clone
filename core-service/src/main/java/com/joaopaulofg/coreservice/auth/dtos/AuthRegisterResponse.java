package com.joaopaulofg.coreservice.auth.dtos;

public record AuthRegisterResponse(
        Long id,
        String email,
        String firstName,
        String lastName
){}

