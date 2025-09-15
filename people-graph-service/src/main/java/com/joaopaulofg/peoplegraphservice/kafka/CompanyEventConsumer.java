package com.joaopaulofg.peoplegraphservice.kafka;

import com.joaopaulofg.peoplegraphservice.domain.Company;
import com.joaopaulofg.peoplegraphservice.service.PeopleGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyEventConsumer {

    private final PeopleGraphService peopleGraphService;

    @KafkaListener(topics = "company-events",
            groupId = "people-graph-group",
            containerFactory = "companyKafkaListenerContainerFactory")
    public void consumeCompanyEvent(CompanyCreatedEvent event) {
        Company company = Company.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .build();

        System.out.println(company.toString());
        peopleGraphService.createCompany(company);
    }
}
