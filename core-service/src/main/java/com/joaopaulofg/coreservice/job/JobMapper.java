package com.joaopaulofg.coreservice.job;

import com.joaopaulofg.coreservice.job.dtos.CreateJobRequest;
import com.joaopaulofg.coreservice.job.dtos.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    JobDto toJobDto(Job job);
    Job toJob(CreateJobRequest request);
}
