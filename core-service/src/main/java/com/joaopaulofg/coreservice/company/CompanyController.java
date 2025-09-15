package com.joaopaulofg.coreservice.company;

import com.joaopaulofg.coreservice.company.dtos.CompanyDto;
import com.joaopaulofg.coreservice.company.dtos.CreateCompanyRequest;
import com.joaopaulofg.coreservice.job.JobService;
import com.joaopaulofg.coreservice.job.dtos.CreateJobRequest;
import com.joaopaulofg.coreservice.job.dtos.JobDto;
import com.joaopaulofg.coreservice.user.dtos.UserDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final JobService jobService;

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody @Valid CreateCompanyRequest createCompanyRequest) {
        return new ResponseEntity<>(companyService.createCompany(createCompanyRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @PostMapping("/{companyId}/jobs")
    public ResponseEntity<JobDto> createJob(@PathVariable Long companyId, @RequestBody @Valid CreateJobRequest createJobRequest) {
        return  new ResponseEntity<>(jobService.createJob(companyId, createJobRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{companyId}/jobs")
    public ResponseEntity<List<JobDto>> getJobs(@PathVariable Long companyId) {
        return new ResponseEntity<>(jobService.getJobsByCompany(companyId), HttpStatus.OK);
    }

    @PostMapping("/{companyId}/hire/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void hireUser(@PathVariable Long companyId, @PathVariable Long userId) {
        companyService.hireUser(companyId, userId);
    }

    @GetMapping("/{companyId}/employees")
    public ResponseEntity<List<UserDto>> getEmployees(@PathVariable Long companyId) {
        return new ResponseEntity<>(companyService.getEmployees(companyId), HttpStatus.OK);
    }
}

