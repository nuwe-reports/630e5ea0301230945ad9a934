package com.easy_job_search.repository;


import com.easy_job_search.entity.Subscriber;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriberRepo extends CrudRepository<Subscriber, Long> {

    Optional<Subscriber> findSubscriberByEmail (String email);
}
