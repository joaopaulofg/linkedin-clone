package com.joaopaulofg.coreservice.job.dtos;

import java.time.LocalDate;

public record JobDto(
        Long id,
        String title,
        String description,
        LocalDate postedAt
) {
}
