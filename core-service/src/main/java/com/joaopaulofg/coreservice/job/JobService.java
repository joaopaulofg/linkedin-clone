package com.joaopaulofg.coreservice.job;

import com.joaopaulofg.coreservice.company.Company;
import com.joaopaulofg.coreservice.company.CompanyRepository;
import com.joaopaulofg.coreservice.job.dtos.CreateJobRequest;
import com.joaopaulofg.coreservice.job.dtos.JobDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class JobService {

    private JobRepository jobRepository;
    private JobMapper jobMapper;
    private CompanyRepository companyRepository;

    public JobDto createJob(Long companyId, CreateJobRequest createJobRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow(()-> new IllegalArgumentException("Company not found"));
        Job job = jobMapper.toJob(createJobRequest);
        job.setCompany(company);
        job.setPostedAt(LocalDate.now());
        return jobMapper.toJobDto(jobRepository.save(job));
    }

    public List<JobDto> getJobsByCompany(Long companyId) {
        return jobRepository.findByCompanyId(companyId)
                .stream()
                .map(jobMapper::toJobDto)
                .toList();
    }

}
