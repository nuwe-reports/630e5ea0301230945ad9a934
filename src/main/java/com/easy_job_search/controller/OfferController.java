package com.easy_job_search.controller;

import com.easy_job_search.dto_input.AttributeId;
import com.easy_job_search.dto_input.AttributeString;
import com.easy_job_search.dto_input.RegisterOffer;
import com.easy_job_search.dto_output.OfferResponse;
import com.easy_job_search.dto_output.OfferTotalRegistered;
import com.easy_job_search.dto_output.OfferWithCandidate;
import com.easy_job_search.utility.JobType;
import com.easy_job_search.utility.ModalityJob;
import com.easy_job_search.entity.Offer;
import com.easy_job_search.helper.Helper;
import com.easy_job_search.service.CandidateService;
import com.easy_job_search.service.CompanyService;
import com.easy_job_search.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
        ResponseEntity<OfferResponse> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try{
            Offer o1 = companyService.addOffer(companyId, offer);
            OfferResponse offerResponse = Helper.convertOfferToOfferResponse(o1);
            return response = new ResponseEntity<>(offerResponse, HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException |NullPointerException ex){
            ex.printStackTrace();
        }
        return response;
    }

    @PutMapping("update-offer")
    public ResponseEntity<OfferResponse> updateOffer(@RequestBody Offer offer) {
        ResponseEntity<OfferResponse> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
            response = new ResponseEntity<>(Helper.convertOfferToOfferResponse(o1), HttpStatus.OK);
        } catch (ClassCastException | IllegalArgumentException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @PostMapping("register-to-offer")
    public ResponseEntity<OfferTotalRegistered> registerToOffer(@RequestBody RegisterOffer registerOffer){
        ResponseEntity<OfferTotalRegistered> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            Offer offer = candidateService.registerToOffer(registerOffer.getCandidateId(), registerOffer.getOfferId());
            OfferTotalRegistered offerTotalRegisted = Helper.convertOfferToOfferTotalRegistered(offer);
            return response = new ResponseEntity<>(offerTotalRegisted, HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException | NullPointerException | DataIntegrityViolationException ex) {
            ex.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        return response;
    }

    @GetMapping("offers-basic-data")
    public ResponseEntity<List<OfferResponse>> getAllOffersWithOutRegistered(){
        List<Offer> offers = offerService.findAllOffer();
        return new ResponseEntity<>(Helper.convertListOfferToListOfferResponse(offers),
                HttpStatus.OK);
    }

    @GetMapping("offers-total-registered")
    public ResponseEntity<List<OfferTotalRegistered>> getAllOffersWithTotalRegistered(){
        List<Offer> offers = offerService.findAllOffer();
        return new ResponseEntity<>(Helper.convertListOfferToListOfferTotalRegistered(offers),
                HttpStatus.OK);
    }

    @GetMapping("offer-byId-totalRegistered")
    public ResponseEntity<OfferTotalRegistered> getOfferByIdWithTotalRegistered(@RequestBody AttributeId idOffer){
        ResponseEntity<OfferTotalRegistered> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try{
            Offer offer = offerService.findOfferById(idOffer.getId());
            response =  new ResponseEntity<>(Helper.convertOfferToOfferTotalRegistered(offer),
                    HttpStatus.OK);
        }catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }
        return response;

    }

    @GetMapping("offer-byId-withCandidates")
    public ResponseEntity<OfferWithCandidate> getOfferByIdWithCandidates(@RequestBody AttributeId idOffer){
        ResponseEntity<OfferWithCandidate> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try{
            Offer offer = offerService.findOfferById(idOffer.getId());
            response =  new ResponseEntity<>(Helper.convertOfferToOfferWithCandidate(offer),
                    HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
        return response;
    }

    @GetMapping("offers-byPosition/{position}")
    public ResponseEntity<List<OfferTotalRegistered>> getOfferByPosition (@PathVariable("position") String position){
        ResponseEntity<List<OfferTotalRegistered>> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            List<Offer> offers = offerService.findOfferByPosition(position);
            if(!offers.isEmpty()){
                response =  new ResponseEntity<>(Helper.convertListOfferToListOfferTotalRegistered(offers),
                        HttpStatus.OK);
            }
        return response;
    }

    @GetMapping("offers-byJobType/{jobType}")
    public ResponseEntity<List<OfferTotalRegistered>> getOfferByJobType (@PathVariable("jobType") JobType jobType){
        ResponseEntity<List<OfferTotalRegistered>> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<Offer> offers = offerService.findOfferByJobType(jobType);
        if(!offers.isEmpty()){
            response =  new ResponseEntity<>(Helper.convertListOfferToListOfferTotalRegistered(offers), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("offers-byModality/{modality}")
    public ResponseEntity<List<OfferTotalRegistered>> getOfferByModalityJob
            (@PathVariable("modality") ModalityJob modality){
        ResponseEntity<List<OfferTotalRegistered>> response =
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<Offer> offers = offerService.findOfferByModality(modality);
        if(!offers.isEmpty()){
            response =  new ResponseEntity<>(Helper.convertListOfferToListOfferTotalRegistered(offers), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("offers-byLocation/{location}")
    public ResponseEntity<List<OfferTotalRegistered>> getOfferByLocation (@PathVariable("location") String location){
        ResponseEntity<List<OfferTotalRegistered>> response = new ResponseEntity<List<OfferTotalRegistered>>(HttpStatus.NO_CONTENT);
        List<Offer> offers = offerService.findOfferByLocation(location);
        if(!offers.isEmpty()){
            response =  new ResponseEntity<>(Helper.convertListOfferToListOfferTotalRegistered(offers), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("offers-bySector/{sector}")
    public ResponseEntity<List<OfferTotalRegistered>> getOfferBySector (@PathVariable("sector") String sector){
        ResponseEntity<List<OfferTotalRegistered>> response = new ResponseEntity<List<OfferTotalRegistered>>(HttpStatus.NO_CONTENT);
        List<Offer> offers = offerService.findOfferBySector(sector);
        if(!offers.isEmpty()){
            response =  new ResponseEntity<>(Helper.convertListOfferToListOfferTotalRegistered(offers), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("offers-bySkill/{skill}")
    public ResponseEntity<List<OfferTotalRegistered>> getOfferBySkill (@PathVariable("skill") String skill){
        ResponseEntity<List<OfferTotalRegistered>> response = new ResponseEntity<List<OfferTotalRegistered>>(HttpStatus.NO_CONTENT);
        List<Offer> offers = offerService.findOfferBySkill(skill);
        if(!offers.isEmpty()){
            response =  new ResponseEntity<>(Helper.convertListOfferToListOfferTotalRegistered(offers), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("offers-byOwnerId")
    public ResponseEntity<List<OfferWithCandidate>> getOfferByOwnerId (@RequestBody AttributeId idCompany){
        ResponseEntity<List<OfferWithCandidate>> response = new ResponseEntity<List<OfferWithCandidate>>
                (HttpStatus.NO_CONTENT);
        try{
            List<Offer> offers = offerService.findOfferByOwnerId(idCompany.getId());
            response =  new ResponseEntity<>(Helper.convertListOfferToListOfferWithCandidates(offers), HttpStatus.OK);
        }catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }
        return response;
    }

    @GetMapping("offers-byOwnerName")
    public ResponseEntity<List<OfferTotalRegistered>> getOfferByOwnerName (@RequestBody AttributeString name){

        ResponseEntity<List<OfferTotalRegistered>> response = new ResponseEntity<List<OfferTotalRegistered>>
                (HttpStatus.NO_CONTENT);

        List<Offer> offers = offerService.findOfferByOwnerName(name.getName());
        if(!offers.isEmpty()){
        response = new ResponseEntity<>(Helper.convertListOfferToListOfferTotalRegistered(offers), HttpStatus.OK);
        }
        return response;
    }

    @DeleteMapping("delete-offer/{id}")
    public ResponseEntity deleteOfferById (@PathVariable("id") long idOffer){

        ResponseEntity response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            offerService.deleteOfferById(idOffer);
            response = ResponseEntity.ok("Removed successfuly");

        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
            response = new ResponseEntity<> (HttpStatus.ALREADY_REPORTED);
        }
        return response;
    }



}
