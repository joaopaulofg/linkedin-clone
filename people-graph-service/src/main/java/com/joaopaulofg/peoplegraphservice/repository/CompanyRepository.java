package com.joaopaulofg.peoplegraphservice.repository;

import com.joaopaulofg.peoplegraphservice.domain.Company;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CompanyRepository extends Neo4jRepository<Company, Long> {
}
