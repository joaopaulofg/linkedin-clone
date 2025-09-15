package com.joaopaulofg.coreservice.company;

import com.joaopaulofg.coreservice.company.dtos.CompanyDto;
import com.joaopaulofg.coreservice.company.dtos.CreateCompanyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company toCompany(CreateCompanyRequest companyRequestDto);

    CompanyDto toCompanyDto(Company company);
}
