package com.joaopaulofg.coreservice.user;

import com.joaopaulofg.coreservice.user.dtos.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUserById(@RequestParam long id){
        return new ResponseEntity<>(userService.getUserById(id),  HttpStatus.OK);
    }
}
