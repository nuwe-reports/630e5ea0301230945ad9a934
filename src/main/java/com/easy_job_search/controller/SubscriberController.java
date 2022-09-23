package com.easy_job_search.controller;

import com.easy_job_search.entity.Subscriber;
import com.easy_job_search.service.EmailService;
import com.easy_job_search.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/easy-job-search/")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private EmailService emailService;

    @PostMapping("subscribe")
    public ResponseEntity<Subscriber> addSubscriber(@Valid @RequestBody Subscriber subscriber) {
        ResponseEntity<Subscriber> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            response = new ResponseEntity<>(subscriberService.addSubscriber(subscriber),
                    HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @DeleteMapping("unsubscribe")
    public ResponseEntity deleteSuscriber(@RequestBody Subscriber email) {
        ResponseEntity response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            subscriberService.deleteSubscriber(email.getEmail());
            response = ResponseEntity.ok("Removed successfuly");

        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        return response;
    }

}
