package com.joaopaulofg.coreservice.domain.service;

import com.joaopaulofg.coreservice.domain.exception.EmailAlreadyRegisteredException;
import com.joaopaulofg.coreservice.domain.model.User;
import com.joaopaulofg.coreservice.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String name, String email, String password,  String headline) {
        // Verificando se já existe algum usuario cadastrado com o mesmo email
        Optional<User> existing = userRepository.findByEmail(email);
        if(existing.isPresent()){
            throw new EmailAlreadyRegisteredException("Email already registered: "  + email);
        }

        User user = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .headline(headline)
                .build();

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
