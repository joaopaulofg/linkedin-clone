package com.joaopaulofg.coreservice.infra.kafka;
import com.joaopaulofg.coreservice.user.User;
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

    public static UserCreatedEvent fromEntity(User user) {
        UserCreatedEvent event = new UserCreatedEvent();
        event.setId(user.getId()); // aqui garante que vai com ID do Postgres
        event.setFirstName(user.getFirstName());
        event.setLastName(user.getLastName());
        event.setEmail(user.getEmail());
        event.setHeadLine(user.getHeadLine());
        return event;
    }

}
