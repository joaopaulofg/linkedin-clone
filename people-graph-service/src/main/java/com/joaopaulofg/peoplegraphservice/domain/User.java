package com.joaopaulofg.peoplegraphservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node("User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long id;  // usa o mesmo id do Postgres

    private String firstName;
    private String lastName;
    private String email;

    @Relationship(type = "CONNECTED_TO")
    private List<User> connections;
}
