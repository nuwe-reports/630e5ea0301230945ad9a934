package com.easy_job_search.repository;

import com.easy_job_search.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepo extends CrudRepository<Company, Long> {

    Optional<List<Company>> findCompanyByFullName(String fullName);

}
