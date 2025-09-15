package com.joaopaulofg.coreservice.auth;

import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterResponse;
import com.joaopaulofg.coreservice.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-15T14:16:21-0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class AuthMapperImpl implements AuthMapper {

    @Override
    public User toUser(AuthRegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( request.email() );
        user.firstName( request.firstName() );
        user.headLine( request.headLine() );
        user.lastName( request.lastName() );
        user.password( request.password() );

        return user.build();
    }

    @Override
    public AuthRegisterResponse toAuthRegisterResponse(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String firstName = null;
        String lastName = null;

        id = user.getId();
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();

        AuthRegisterResponse authRegisterResponse = new AuthRegisterResponse( id, email, firstName, lastName );

        return authRegisterResponse;
    }
}
