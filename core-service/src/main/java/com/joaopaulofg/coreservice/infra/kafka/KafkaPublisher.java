package com.joaopaulofg.coreservice.infra.kafka;

import com.joaopaulofg.coreservice.company.Company;
import com.joaopaulofg.coreservice.company.dtos.CompanyDto;
import com.joaopaulofg.coreservice.user.User;
import com.joaopaulofg.coreservice.user.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendUserCreatedEvent(User user) {
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.fromEntity(user);
        kafkaTemplate.send("user-events", "USER_CREATED", userCreatedEvent);
    }

    public void sendCompanyCreatedEvent(Company company) {
        CompanyCreatedEvent companyCreatedEvent = CompanyCreatedEvent.fromEntity(company);
        kafkaTemplate.send("company-events", "COMPANY_CREATED", companyCreatedEvent);
    }

}
