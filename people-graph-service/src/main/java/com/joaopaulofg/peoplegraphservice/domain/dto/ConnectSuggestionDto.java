package com.joaopaulofg.peoplegraphservice.domain.dto;

import com.joaopaulofg.peoplegraphservice.domain.User;
import lombok.Data;

@Data
public class ConnectSuggestionDto {
    User suggestion;
    Long mutualConnections;
}
