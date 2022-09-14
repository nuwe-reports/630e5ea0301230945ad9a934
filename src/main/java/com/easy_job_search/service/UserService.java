package com.easy_job_search.service;

import com.easy_job_search.entity.User;
import com.easy_job_search.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> findAllUser(){
        return (List<User>) userRepo.findAll();
    }

    public User findUserById(long id){
        Optional<User> op = userRepo.findUserById(id);
        return op.orElse(null);
    }

    public User findUserByEmail(String email){
        Optional<User> op = userRepo.findUserByEmail(email);
        return op.orElse(null);
    }

    public void deleteUserById(Long id){
        userRepo.deleteById(id);
    }

    public void deleteUserByEmail(String email){
        User user = this.findUserByEmail(email);
        userRepo.delete(user);
    }

}
