package com.joaopaulofg.peoplegraphservice.service;

import com.joaopaulofg.peoplegraphservice.domain.User;
import com.joaopaulofg.peoplegraphservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PeopleGraphService {

    private final UserRepository userRepository;

    public User createUserNode(User user) {
        return userRepository.save(user);
    }

    // conectar usuários
    public void connectUsers(Long userId, Long targetId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User " + userId + " not found"));
        User target = userRepository.findById(targetId)
                .orElseThrow(() -> new RuntimeException("User " + targetId + " not found"));

        if(user.getConnections() == null) {
            user.setConnections(new ArrayList<>());
        }
        user.getConnections().add(target);
        userRepository.save(user);
    }
}

