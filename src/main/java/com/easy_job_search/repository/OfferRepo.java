package com.easy_job_search.repository;

import com.easy_job_search.entity.Offer;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepo extends CrudRepository<Offer, Long> {
}
