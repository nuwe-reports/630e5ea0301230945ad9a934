package com.easy_job_search.repository;

import com.easy_job_search.entity.Candidate;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepo extends CrudRepository<Candidate, Long> {



}
