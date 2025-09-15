package com.joaopaulofg.peoplegraphservice.repository;

import com.joaopaulofg.peoplegraphservice.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, Long> {
}
