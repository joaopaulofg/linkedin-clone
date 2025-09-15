package com.joaopaulofg.coreservice.job;

import com.joaopaulofg.coreservice.job.dtos.CreateJobRequest;
import com.joaopaulofg.coreservice.job.dtos.JobDto;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-15T14:16:22-0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public JobDto toJobDto(Job job) {
        if ( job == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String description = null;
        LocalDate postedAt = null;

        id = job.getId();
        title = job.getTitle();
        description = job.getDescription();
        postedAt = job.getPostedAt();

        JobDto jobDto = new JobDto( id, title, description, postedAt );

        return jobDto;
    }

    @Override
    public Job toJob(CreateJobRequest request) {
        if ( request == null ) {
            return null;
        }

        Job.JobBuilder job = Job.builder();

        job.description( request.description() );
        job.title( request.title() );

        return job.build();
    }
}
