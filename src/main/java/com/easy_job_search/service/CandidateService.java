package com.easy_job_search.service;

import com.easy_job_search.entity.Candidate;
import com.easy_job_search.entity.Offer;
import com.easy_job_search.repository.CandidateRepo;
import com.easy_job_search.repository.OfferRepo;
import com.easy_job_search.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private UserService userService;
    @Autowired
    private CandidateRepo candidateRepo;
    @Autowired
    private OfferService offerService;

    public Candidate createCandidate(Candidate candidate){
        return candidateRepo.save(candidate);
    }

    public Candidate updateCandidate(Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    public List<Candidate> findAllCandidate(){
        return (List<Candidate>) candidateRepo.findAll();
    }

    public Offer registerToOffer(long idUser, long idOffer){
        Candidate candidate = (Candidate) userService.findUserById(idUser);
        Offer offer = offerService.findOfferById(idOffer);
        candidate.getOffersRegisterIds().add(offer.getId());
        offer.getCandidates().add(candidate);
        this.updateCandidate(candidate);
        return offerService.updateOffer(offer);
    }

}
