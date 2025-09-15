package com.joaopaulofg.coreservice.auth;

import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterRequest;
import com.joaopaulofg.coreservice.auth.dtos.AuthRegisterResponse;
import com.joaopaulofg.coreservice.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-14T17:26:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
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
        user.password( request.password() );
        user.firstName( request.firstName() );
        user.lastName( request.lastName() );
        user.headLine( request.headLine() );

        return user.build();
    }

    @Override
    public AuthRegisterResponse toAuthRegisterResponse(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;
        String firstName = null;
        String lastName = null;

        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();

        AuthRegisterResponse authRegisterResponse = new AuthRegisterResponse( email, firstName, lastName );

        return authRegisterResponse;
    }
}
