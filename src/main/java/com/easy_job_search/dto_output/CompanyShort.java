package com.easy_job_search.dto_output;

import com.easy_job_search.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class CompanyShort {
    private String fullName;
    private String email;
}
