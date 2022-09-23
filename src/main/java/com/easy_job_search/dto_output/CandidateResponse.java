package com.easy_job_search.dto_output;

import com.easy_job_search.entity.Education;
import com.easy_job_search.entity.WorkExperience;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CandidateResponse {
    private long id;
    private String fullName;
    private String email;
    private String profile;
    private List<Education> educationList;
    private List<WorkExperience> workExperienceList;
    private List<Long> offersRegisterIds;
}
