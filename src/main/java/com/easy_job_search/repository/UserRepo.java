package com.easy_job_search.repository;

import com.easy_job_search.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findUserById(long id);

    Optional<User> findUserByEmail(String email);

}
