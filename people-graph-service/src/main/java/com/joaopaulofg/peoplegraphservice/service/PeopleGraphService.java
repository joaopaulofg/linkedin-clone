package com.joaopaulofg.peoplegraphservice.service;

import com.joaopaulofg.peoplegraphservice.domain.Company;
import com.joaopaulofg.peoplegraphservice.domain.User;
import com.joaopaulofg.peoplegraphservice.domain.dto.ConnectSuggestionDto;
import com.joaopaulofg.peoplegraphservice.domain.dto.ShortestPathDto;
import com.joaopaulofg.peoplegraphservice.repository.CompanyRepository;
import com.joaopaulofg.peoplegraphservice.repository.UserRepository;
import com.joaopaulofg.peoplegraphservice.utils.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public List<Company> getCompanies() {}

    public void connectUsers(Long userId, Long targetId) {
        User user = getUserOrThrow(userId);
        User target = getUserOrThrow(targetId);

        if(user.getConnections() == null) {
            user.setConnections(new ArrayList<>());
        }
        user.getConnections().add(target);
        userRepository.save(user);
    }

    public void addWorkedAt(Long userId, Long companyId) {
        User user = getUserOrThrow(userId);
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new UserNotFoundException("Company " + companyId + " not found"));

        if(user.getWorkedAts() == null) {
            user.setWorkedAts(new ArrayList<>());
        }

        user.getWorkedAts().add(company);
        userRepository.save(user);
    }

    public void followUser(Long followerId, Long followedId) {
        User follower = getUserOrThrow(followerId);
        User followed = getUserOrThrow(followedId);

        follower.getFollows().add(followed);
        userRepository.save(follower);
    }

    public List<User> getUserConnections(Long userId) {
        getUserOrThrow(userId);
        return userRepository.getConnections(userId);
    }

    public List<User> getUserFollowing(Long userId) {
        getUserOrThrow(userId);
        return userRepository.getFollowing(userId);
    }

    public List<ConnectSuggestionDto> findSuggestions(Long userId) {
        getUserOrThrow(userId);
        return userRepository.findSuggestions(userId);
    }

    public List<User> findShortestPath(Long from, Long to) {
        return userRepository.findShortestPath(from, to);
    }

    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User " + userId + " not found"));
    }
}

