package com.easy_job_search.service;

import com.easy_job_search.entity.Offer;
import com.easy_job_search.entity.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SubscriberService subscriberService;

    @Value("${spring.mail.username}")
    private String email;

    public void sendEmailToAllSubs(Offer offer) {
        List<Subscriber> subs = subscriberService.findAllSubscribers();
        for (Subscriber sub: subs) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email);
            message.setTo(sub.getEmail());
            message.setSubject("Nueva oferta - " + offer.getPosition() + " en " + offer.getLocation());
            message.setText("DESCRIPCION: " + offer.getDescription() + "\n" +
                    "SECTOR: " + offer.getSector() + "\n" +
                    "JOB TYPE: " + offer.getJobType() + "\n" +
                    "MODALITY JOB: " + offer.getModalityJob() + "\n" +
                    "COMPANY: " + offer.getOwner().getFullName() + "\n" +
                    "SKILLS: " + Arrays.toString(offer.getSkills()));
            javaMailSender.send(message);
        }
    }
}
