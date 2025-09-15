package com.joaopaulofg.coreservice.user;

import com.joaopaulofg.coreservice.infra.security.JwtProvider;
import com.joaopaulofg.coreservice.user.dtos.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public User register(User newUser) {
        if(userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new UserEmailAlreadyRegisteredException("Email already registered");
        }

        User user = User.builder()
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .headLine(newUser.getHeadLine())
                .build();

        return userRepository.save(user);
    }

    public boolean login(String email,  String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return passwordEncoder.matches(password, user.getPassword());
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

}
