package com.joaopaulofg.peoplegraphservice.kafka;

import com.joaopaulofg.peoplegraphservice.domain.User;
import com.joaopaulofg.peoplegraphservice.service.PeopleGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventConsumer {

    private final PeopleGraphService peopleGraphService;

    @KafkaListener(topics = "user-events",
            groupId = "people-graph-group",
            containerFactory = "userKafkaListenerContainerFactory"
    )
    public void consumeUserEvent(UserCreatedEvent event) {
        User user = User.builder()
                .id(event.getId())  // agora vem do Postgres
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .email(event.getEmail())
                .build();

        System.out.println(user.toString());
        peopleGraphService.createUserNode(user);
    }

}