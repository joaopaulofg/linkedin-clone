package com.joaopaulofg.coreservice.auth.dtos;

public record AuthRegisterRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        String headLine
){}
