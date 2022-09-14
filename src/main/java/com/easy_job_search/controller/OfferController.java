package com.easy_job_search.controller;

import com.easy_job_search.dto_input.RegisterOffer;
import com.easy_job_search.dto_output.OfferResponse;
import com.easy_job_search.dto_output.OfferTotalRegistered;
import com.easy_job_search.entity.Offer;
import com.easy_job_search.helper.Helper;
import com.easy_job_search.service.CandidateService;
import com.easy_job_search.service.CompanyService;
import com.easy_job_search.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/easy-job-search/")
public class OfferController {
    @Autowired
    private OfferService offerService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CandidateService candidateService;

    @PostMapping("create-offer/{id}")
    public ResponseEntity<OfferResponse> addOffer(@PathVariable("id") long companyId, @RequestBody Offer offer){
        ResponseEntity<OfferResponse> response = new ResponseEntity<OfferResponse>(HttpStatus.NO_CONTENT);
        try{
            Offer o1 = companyService.addOffer(companyId, offer);
            OfferResponse offerResponse = Helper.convertOfferToOfferResponse(offer);
            return response = new ResponseEntity<OfferResponse>(offerResponse, HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException |NullPointerException ex){
            ex.printStackTrace();
        }
        return response;
    }

    @PutMapping("update-offer")
    public ResponseEntity<OfferResponse> updateOffer(@RequestBody Offer offer) {
        ResponseEntity<OfferResponse> response = new ResponseEntity<OfferResponse>(HttpStatus.NO_CONTENT);
        try {
            Offer o1 = offerService.findOfferById(offer.getId());
            if(offer.getPosition() != null){
                o1.setPosition(offer.getPosition());
            } else if (offer.getJobType() != null) {
                o1.setJobType(offer.getJobType());
            } else if (offer.getModalityJob() != null) {
                o1.setModalityJob(offer.getModalityJob());
            } else if (offer.getLocation() != null) {
                o1.setLocation(offer.getLocation());
            } else if (offer.getSector() != null) {
                o1.setSector(offer.getSector());
            } else if (offer.getDescription() != null) {
                o1.setDescription(offer.getDescription());
            }  else if (offer.getSkills() != null) {
                o1.setSkills(offer.getSkills());
            }
            offerService.updateOffer(o1);
            response = new ResponseEntity<OfferResponse>(Helper.convertOfferToOfferResponse(o1), HttpStatus.OK);
        } catch (ClassCastException | IllegalArgumentException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @PostMapping("register-to-offer")
    public ResponseEntity<OfferTotalRegistered> registerToOffer(@RequestBody RegisterOffer registerOffer){
        ResponseEntity<OfferTotalRegistered> response = new ResponseEntity<OfferTotalRegistered>(HttpStatus.NO_CONTENT);
        try{
            Offer offer = candidateService.registerToOffer(registerOffer.getCandidateId(), registerOffer.getOfferId());
            OfferTotalRegistered offerTotalRegisted = Helper.convertOfferToOfferTotalRegistered(offer);
            return response = new ResponseEntity<OfferTotalRegistered>(offerTotalRegisted, HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @GetMapping("offers-basic-data")
    private ResponseEntity<List<OfferResponse>> findAllOffersWithOutRegistered(){
        List<Offer> offers = offerService.findAllOffer();
        return new ResponseEntity<List<OfferResponse>>(Helper.convertListOfferToListOfferResponse(offers),
                HttpStatus.OK);
    }

    @GetMapping("offers-numbers-registered")
    private ResponseEntity<List<OfferTotalRegistered>> findAllOffersWithRegistered(){
        List<Offer> offers = offerService.findAllOffer();
        return new ResponseEntity<List<OfferTotalRegistered>>(Helper.convertListOfferToOfferTotalRegistered(offers),
                HttpStatus.OK);
    }

}
