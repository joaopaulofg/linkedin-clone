package com.joaopaulofg.coreservice.job.dtos;

public record CreateJobRequest(
        String title,
        String description
) {
}
