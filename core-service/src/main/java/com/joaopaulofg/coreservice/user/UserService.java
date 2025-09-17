package com.joaopaulofg.coreservice.user;

import com.joaopaulofg.coreservice.infra.kafka.KafkaPublisher;
import com.joaopaulofg.coreservice.user.dtos.UserDto;
import com.joaopaulofg.coreservice.user.exceptions.EmailAlreadyExistsException;
import com.joaopaulofg.coreservice.user.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final KafkaPublisher kafkaPublisher;

    public User register(User newUser) {
        if(userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        User user = User.builder()
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .headLine(newUser.getHeadLine())
                .build();

        User savedUser = userRepository.save(user);

        kafkaPublisher.sendUserCreatedEvent(savedUser);
        return savedUser;
    }

    public UserDto getUserById(long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    public List<UserDto> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::toUserDto)
                .toList();
    }

    public Optional<User> findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}