package com.easy_job_search.service;

import com.easy_job_search.entity.Subscriber;
import com.easy_job_search.repository.SubscriberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepo subscriberRepo;

    public Subscriber addSubscriber(Subscriber subscriber){
        return subscriberRepo.save(subscriber);
    }

    public List<Subscriber> findAllSubscribers(){
        return (List<Subscriber>) subscriberRepo.findAll();
    }

    public Subscriber findSubscriberByEmail (String email){
        Optional<Subscriber> op = subscriberRepo.findSubscriberByEmail(email);
        return op.orElse(null);
    }

    public void deleteSubscriber(String email){
        Subscriber subscriber = this.findSubscriberByEmail(email);
        subscriberRepo.delete(subscriber);
    }

}
