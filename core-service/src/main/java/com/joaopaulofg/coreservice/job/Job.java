package com.joaopaulofg.coreservice.job;

import com.joaopaulofg.coreservice.company.Company;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDate postedAt;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
