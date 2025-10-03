package com.joaopaulofg.coreservice.user.dtos;

import com.joaopaulofg.coreservice.company.dtos.CompanyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String headLine;
    private String email;
    private CompanyDto company;
    private LocalDateTime updatedAt;

}

