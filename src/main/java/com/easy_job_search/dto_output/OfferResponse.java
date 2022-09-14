package com.easy_job_search.dto_output;

import com.easy_job_search.entity.Candidate;
import com.easy_job_search.entity.Company;
import com.easy_job_search.entity.JobType;
import com.easy_job_search.entity.ModalityJob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OfferResponse {
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
    private CompanyShort owner;
}
