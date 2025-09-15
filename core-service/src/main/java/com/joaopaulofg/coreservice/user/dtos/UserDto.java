package com.joaopaulofg.coreservice.user.dtos;

import com.joaopaulofg.coreservice.company.dtos.CompanyDto;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String headLine,
        String email,
        CompanyDto company,
        LocalDateTime updatedAt
) {
}
