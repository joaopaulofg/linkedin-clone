package com.joaopaulofg.peoplegraphservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatedEvent {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String headLine;
    private String company;

    // getters e setters
}
