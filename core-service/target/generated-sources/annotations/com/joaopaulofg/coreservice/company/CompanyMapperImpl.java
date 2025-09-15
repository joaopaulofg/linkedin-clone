package com.joaopaulofg.coreservice.company;

import com.joaopaulofg.coreservice.company.dtos.CompanyDto;
import com.joaopaulofg.coreservice.company.dtos.CreateCompanyRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-15T14:16:22-0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public Company toCompany(CreateCompanyRequest companyRequestDto) {
        if ( companyRequestDto == null ) {
            return null;
        }

        Company.CompanyBuilder company = Company.builder();

        company.description( companyRequestDto.description() );
        company.name( companyRequestDto.name() );

        return company.build();
    }

    @Override
    public CompanyDto toCompanyDto(Company company) {
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
