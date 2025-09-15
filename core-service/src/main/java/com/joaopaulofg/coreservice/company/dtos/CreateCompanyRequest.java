package com.joaopaulofg.coreservice.company.dtos;

public record CreateCompanyRequest(
        String name,
        String description
) {
}
