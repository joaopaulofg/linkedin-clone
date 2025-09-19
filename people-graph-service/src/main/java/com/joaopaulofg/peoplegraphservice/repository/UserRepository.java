package com.joaopaulofg.peoplegraphservice.repository;

import com.joaopaulofg.peoplegraphservice.domain.User;
import com.joaopaulofg.peoplegraphservice.domain.dto.ConnectSuggestionDto;
import com.joaopaulofg.peoplegraphservice.domain.dto.ShortestPathDto;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import java.util.List;
import java.util.Map;

public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("""
        MATCH (u:User {id: $userId})-[:CONNECTED_TO]->(connection:User)
        RETURN connection
        """)
    List<User> getConnections(Long userId);

    @Query("""
        MATCH (u:User {id: $userId})-[:FOLLOWS]->(follow:User)
        RETURN follow
        """)
    List<User> getFollowing(Long userId);

    @Query("""
        MATCH (u:User {id: $userId})-[:CONNECTED_TO]->(friend:User)-[:CONNECTED_TO]->(suggestion:User)
        WHERE NOT (u)-[:CONNECTED_TO]->(suggestion) 
          AND u <> suggestion
        RETURN DISTINCT suggestion, COUNT(friend) AS mutualConnections
        ORDER BY mutualConnections DESC
        LIMIT 10
        """)
    List<ConnectSuggestionDto> findSuggestions(Long userId);

    @Query("""
        MATCH (start:User {id: $from})
        MATCH (end:User {id: $to})
        MATCH p = shortestPath((start)-[:CONNECTED_TO*..10]-(end))
        RETURN nodes(p) AS users
        """)
    List<User> findShortestPath(Long from, Long to);
}
