package com.easy_job_search.service;

import com.easy_job_search.entity.Subscriber;
import com.easy_job_search.repository.SubscriberRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SubscriberServiceTest {

    @Mock
    private SubscriberRepo subscriberRepo;

    @InjectMocks
    private SubscriberService subscriberService;

    private Subscriber subscriber;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subscriber = new Subscriber();
        subscriber.setId(1);
        subscriber.setEmail("email@email.com");
    }

    @Test
    void addSubscriber() {
        when(subscriberRepo.save(any(Subscriber.class))).thenReturn(subscriber);
        assertEquals(subscriberService.addSubscriber(subscriber), subscriber);
    }

    @Test
    void findAllSubscribers() {
        when(subscriberRepo.findAll()).thenReturn(Arrays.asList(subscriber));
        assertEquals(subscriberService.findAllSubscribers(), Arrays.asList(subscriber));
    }

    @Test
    void findSubscriberByEmail() {
        when(subscriberRepo.findSubscriberByEmail(subscriber.getEmail())).thenReturn(Optional.of(subscriber));
        assertEquals(subscriberService.findSubscriberByEmail("email@email.com"), subscriber);
    }

}