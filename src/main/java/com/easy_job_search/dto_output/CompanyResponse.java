package com.easy_job_search.dto_output;

import com.easy_job_search.entity.Address;
import com.easy_job_search.entity.Education;
import com.easy_job_search.entity.WorkExperience;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CompanyResponse {
    private long id;
    private String fullName;
    private String email;
    private Address address;

}
