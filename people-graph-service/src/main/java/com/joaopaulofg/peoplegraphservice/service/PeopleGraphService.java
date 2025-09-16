package com.joaopaulofg.peoplegraphservice.service;

import com.joaopaulofg.peoplegraphservice.domain.Company;
import com.joaopaulofg.peoplegraphservice.domain.User;
import com.joaopaulofg.peoplegraphservice.repository.CompanyRepository;
import com.joaopaulofg.peoplegraphservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PeopleGraphService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public void createUserNode(User user) {
        userRepository.save(user);
    }

    public void createCompany(Company company) {
        companyRepository.save(company);
    }

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

    public void addWorkedAt(Long userId, Long companyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User " + userId + " not found"));
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company " + companyId + " not found"));
        if(user.getWorkedAts() == null) {
            user.setWorkedAts(new ArrayList<>());
        }
        user.getWorkedAts().add(company);
        userRepository.save(user);
    }

    public void followUser(Long followerId, Long followedId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("User " + followerId + " not found"));
        User followed = userRepository.findById(followedId)
                .orElseThrow(() -> new RuntimeException("User " + followedId + " not found"));

        follower.getFollows().add(followed);
        userRepository.save(follower);
    }
}

