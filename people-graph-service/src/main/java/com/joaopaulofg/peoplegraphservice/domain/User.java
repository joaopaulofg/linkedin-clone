package com.joaopaulofg.peoplegraphservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.List;

@Node("User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @Relationship(type = "CONNECTED_TO")
    private List<User> connections;

    @Relationship(type = "WORKED_AT")
    private List<Company> workedAts;
}
