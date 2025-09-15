package com.joaopaulofg.coreservice.company;

import com.joaopaulofg.coreservice.company.dtos.CompanyDto;
import com.joaopaulofg.coreservice.company.dtos.CreateCompanyRequest;
import com.joaopaulofg.coreservice.user.User;
import com.joaopaulofg.coreservice.user.UserMapper;
import com.joaopaulofg.coreservice.user.UserRepository;
import com.joaopaulofg.coreservice.user.dtos.UserDto;
import com.joaopaulofg.coreservice.user.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;

    public CompanyDto createCompany(CreateCompanyRequest request){
        return companyMapper.toCompanyDto(companyRepository.save(companyMapper.toCompany(request)));
    }

    public CompanyDto getCompanyById(Long id){
        return companyRepository.findById(id)
                .map(companyMapper::toCompanyDto)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
    }

    public void hireUser(Long companyId, Long userId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        User user  = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setCompany(company);
        userRepository.save(user);
    }

    public List<UserDto> getEmployees(Long companyId){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));

        return company.getEmployees()
                .stream()
                .map(userMapper::toUserDto)
                .toList();
    }
}
