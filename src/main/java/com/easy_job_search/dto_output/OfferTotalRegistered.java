package com.easy_job_search.dto_output;

import com.easy_job_search.entity.JobType;
import com.easy_job_search.entity.ModalityJob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class OfferTotalRegistered {
    private long id;
    private String position;
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    @Enumerated(EnumType.STRING)
    private ModalityJob modalityJob;
    private String location;
    private String sector;
    private String description;
    private String[] skills;
    private LocalDate releaseDate;
    private CompanySimplify owner;
    private long totalRegistered;
}
