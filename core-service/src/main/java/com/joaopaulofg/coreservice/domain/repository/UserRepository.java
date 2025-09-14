package com.joaopaulofg.coreservice.domain.repository;

import com.joaopaulofg.coreservice.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
