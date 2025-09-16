package com.joaopaulofg.peoplegraphservice.web;

import com.joaopaulofg.peoplegraphservice.service.PeopleGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graph")
@RequiredArgsConstructor
public class PeopleGraphController {

    private final PeopleGraphService peopleGraphService;

    @PostMapping("/connect/{userId}/{targetId}")
    public ResponseEntity<Void> connectUsers(@PathVariable Long userId, @PathVariable Long targetId) {
        peopleGraphService.connectUsers(userId, targetId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/worked/{userId}/{companyId}")
    public ResponseEntity<Void> addWorkedAt(@PathVariable Long userId, @PathVariable Long companyId) {
        peopleGraphService.addWorkedAt(userId, companyId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/follow/{followerId}/{followedId}")
    public ResponseEntity<Void> followUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        peopleGraphService.followUser(followerId, followedId);
        return ResponseEntity.ok().build();
    }

}

