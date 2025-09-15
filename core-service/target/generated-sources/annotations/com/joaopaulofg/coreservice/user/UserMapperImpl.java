package com.joaopaulofg.coreservice.user;

import com.joaopaulofg.coreservice.company.Company;
import com.joaopaulofg.coreservice.company.dtos.CompanyDto;
import com.joaopaulofg.coreservice.user.dtos.UserDto;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-15T14:16:22-0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.company( companyDtoToCompany( userDto.company() ) );
        user.email( userDto.email() );
        user.firstName( userDto.firstName() );
        user.headLine( userDto.headLine() );
        user.id( userDto.id() );
        user.lastName( userDto.lastName() );
        if ( userDto.updatedAt() != null ) {
            user.updatedAt( userDto.updatedAt().toLocalDate() );
        }

        return user.build();
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        String headLine = null;
        String email = null;
        CompanyDto company = null;
        LocalDateTime updatedAt = null;

        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        headLine = user.getHeadLine();
        email = user.getEmail();
        company = companyToCompanyDto( user.getCompany() );
        if ( user.getUpdatedAt() != null ) {
            updatedAt = user.getUpdatedAt().atStartOfDay();
        }

        UserDto userDto = new UserDto( id, firstName, lastName, headLine, email, company, updatedAt );

        return userDto;
    }

    protected Company companyDtoToCompany(CompanyDto companyDto) {
        if ( companyDto == null ) {
            return null;
        }

        Company.CompanyBuilder company = Company.builder();

        company.description( companyDto.description() );
        company.id( companyDto.id() );
        company.name( companyDto.name() );

        return company.build();
    }

    protected CompanyDto companyToCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = company.getId();
        name = company.getName();
        description = company.getDescription();

        CompanyDto companyDto = new CompanyDto( id, name, description );

        return companyDto;
    }
}
