package com.easy_job_search.dto_output;

import com.easy_job_search.utility.JobType;
import com.easy_job_search.utility.ModalityJob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    private CompanySimplify owner;
}
