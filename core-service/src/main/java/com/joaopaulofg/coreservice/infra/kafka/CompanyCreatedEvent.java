package com.joaopaulofg.coreservice.infra.kafka;

import com.joaopaulofg.coreservice.company.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyCreatedEvent {
    private Long id;
    private String name;
    private String description;

    public static CompanyCreatedEvent fromEntity(Company company) {
        CompanyCreatedEvent event = new CompanyCreatedEvent();
        event.setId(company.getId());
        event.setName(company.getName());
        event.setDescription(company.getDescription());
        return event;
    }
}
