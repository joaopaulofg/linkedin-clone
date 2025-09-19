package com.joaopaulofg.peoplegraphservice.web;

import com.joaopaulofg.peoplegraphservice.domain.User;
import com.joaopaulofg.peoplegraphservice.domain.dto.ConnectSuggestionDto;
import com.joaopaulofg.peoplegraphservice.service.PeopleGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/graph")
@RequiredArgsConstructor
public class PeopleGraphController {

    private final PeopleGraphService peopleGraphService;

    @PostMapping
    public ResponseEntity<List<User>> findAllUsers() {
        peopleGraphService.findAllUsers();
        return new ResponseEntity<>(peopleGraphService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/connect/{userId}/{targetId}")
    public ResponseEntity<String> connectUsers(@PathVariable Long userId, @PathVariable Long targetId) {
        peopleGraphService.connectUsers(userId, targetId);
        return new  ResponseEntity<>("CONNECTED_T0 "+ userId + "->" + targetId, HttpStatus.OK);
    }

    @GetMapping("/{userId}/connections")
    public ResponseEntity<List<User>> getUserConnections(@PathVariable Long userId) {
        return new  ResponseEntity<>(peopleGraphService.getUserConnections(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<User>> getUserFollowing(@PathVariable Long userId) {
        return new  ResponseEntity<>(peopleGraphService.getUserFollowing(userId), HttpStatus.OK);
    }

    @PostMapping("/worked/{userId}/{companyId}")
    public ResponseEntity<String> addWorkedAt(@PathVariable Long userId, @PathVariable Long companyId) {
        peopleGraphService.addWorkedAt(userId, companyId);
        return new  ResponseEntity<>("WORKED_AT "+ userId + "->" + companyId, HttpStatus.OK);
    }

    @PostMapping("/follow/{followerId}/{followedId}")
    public ResponseEntity<Void> followUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        peopleGraphService.followUser(followerId, followedId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/suggestions/{userId}")
    public ResponseEntity<List<ConnectSuggestionDto> > findSuggestions(@PathVariable Long userId) {
        peopleGraphService.findSuggestions(userId);
        return new ResponseEntity<>(peopleGraphService.findSuggestions(userId), HttpStatus.OK);
    }

    @GetMapping("/shortest-path")
    public ResponseEntity<?>  getShortestPath(@RequestParam Long idFrom, @RequestParam Long idTo) {
        List<User> path = peopleGraphService.findShortestPath(idFrom, idTo);

        if(path == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No path found between users " + idFrom + " and " + idTo));
        }

        return new  ResponseEntity<>(path, HttpStatus.OK);
    }

}

