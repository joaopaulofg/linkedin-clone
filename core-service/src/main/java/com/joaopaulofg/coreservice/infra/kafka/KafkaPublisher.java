package com.joaopaulofg.coreservice.infra.kafka;

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

    public void sendCompanyCreatedEvent(CompanyDto companyDto) {
        kafkaTemplate.send("company-events", "COMPANY_CREATED", companyDto);
    }

//    public void sendUserHiredEvent(Long userId, Long companyId) {
//        Map<String, Object> payload = new HashMap<>();
//        payload.put("userId", userId);
//        payload.put("companyId", companyId);
//        kafkaTemplate.send("user-events", "USER_HIRED", payload);
//    }
}
