package com.joaopaulofg.peoplegraphservice.domain.dto;

import com.joaopaulofg.peoplegraphservice.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class ShortestPathDto {
    List<User> users;
}
