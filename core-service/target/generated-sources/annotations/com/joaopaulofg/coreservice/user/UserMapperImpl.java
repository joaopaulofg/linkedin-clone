package com.joaopaulofg.coreservice.user;

import com.joaopaulofg.coreservice.company.Company;
import com.joaopaulofg.coreservice.company.dtos.CompanyDto;
import com.joaopaulofg.coreservice.user.dtos.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-02T21:16:37-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.email( userDto.getEmail() );
        user.firstName( userDto.getFirstName() );
        user.lastName( userDto.getLastName() );
        user.headLine( userDto.getHeadLine() );
        if ( userDto.getUpdatedAt() != null ) {
            user.updatedAt( userDto.getUpdatedAt().toLocalDate() );
        }
        user.company( companyDtoToCompany( userDto.getCompany() ) );

        return user.build();
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setHeadLine( user.getHeadLine() );
        userDto.setEmail( user.getEmail() );
        userDto.setCompany( companyToCompanyDto( user.getCompany() ) );
        if ( user.getUpdatedAt() != null ) {
            userDto.setUpdatedAt( user.getUpdatedAt().atStartOfDay() );
        }

        return userDto;
    }

    protected Company companyDtoToCompany(CompanyDto companyDto) {
        if ( companyDto == null ) {
            return null;
        }

        Company.CompanyBuilder company = Company.builder();

        company.id( companyDto.id() );
        company.name( companyDto.name() );
        company.description( companyDto.description() );

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
