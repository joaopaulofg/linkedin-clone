package com.joaopaulofg.coreservice.user.dtos;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String headLine,
        String email,
        LocalDateTime updatedAt
) {
}
