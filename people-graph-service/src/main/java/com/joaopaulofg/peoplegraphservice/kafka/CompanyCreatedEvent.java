package com.joaopaulofg.peoplegraphservice.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyCreatedEvent {
    private Long id;
    private String name;
    private String description;
}
