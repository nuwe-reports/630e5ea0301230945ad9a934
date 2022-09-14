package com.easy_job_search.service;

import com.easy_job_search.entity.JobType;
import com.easy_job_search.entity.ModalityJob;
import com.easy_job_search.entity.Offer;
import com.easy_job_search.repository.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    @Autowired
    private OfferRepo offerRepo;

    public Offer createOffer(Offer offer){
        return offerRepo.save(offer);
    }

    public Offer updateOffer(Offer offer){
        return offerRepo.save(offer);
    }

    public List<Offer> findAllOffer(){
        return (List<Offer>) offerRepo.findAll();
    }

    public Offer findOfferById(Long id){
        Optional<Offer> op = offerRepo.findById(id);
        return op.orElse(null);
    }

    public List<Offer> findOfferByPosition(String position){
        List<Offer> offers = this.findAllOffer();
        List<Offer> offersByPosition = offers.stream()
                .filter(x -> x.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());
        return offersByPosition;
    }

    public List<Offer> findOfferByJobType(JobType jobType){
        List<Offer> offers = this.findAllOffer();
        List<Offer> offersByJobType = offers.stream()
                .filter(x -> x.getJobType().name().equalsIgnoreCase(jobType.name()))
                .collect(Collectors.toList());
        return offersByJobType;
    }

    public List<Offer> findOfferByModality(ModalityJob modalityJob){
        List<Offer> offers = this.findAllOffer();
        List<Offer> offersByModalityJob = offers.stream()
                .filter(x -> x.getModalityJob().name().equalsIgnoreCase(modalityJob.name()))
                .collect(Collectors.toList());
        return offersByModalityJob;
    }

    public List<Offer> findOfferByLocation(String location){
        List<Offer> offers = this.findAllOffer();
        List<Offer> offersByLocation = offers.stream()
                .filter(x -> x.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
        return offersByLocation;
    }

    public List<Offer> findOfferBySector(String sector){
        List<Offer> offers = this.findAllOffer();
        List<Offer> offersBySector = offers.stream()
                .filter(x -> x.getSector().equalsIgnoreCase(sector))
                .collect(Collectors.toList());
        return offersBySector;
    }

    public List<Offer> findOfferBySkill(String skill){
        List<Offer> offers = this.findAllOffer();
        List<Offer> offersBySkill = new ArrayList<>();
        offers.stream().forEach( x ->
        {
            if(x.getSkills().equals(skill)){
                offersBySkill.add(x);
            }
        });
//        for(Offer o: offers){
//            if(o.getSkills().contains(skill)){
//                offersBySkill.add(o);
//            }
//        }
        return offersBySkill;
    }

    public List<Offer> findOfferByReleaseDate(String localDate){
        LocalDate.parse(localDate);
        List<Offer> offers = this.findAllOffer();
        List<Offer> offersByReleaseDate = offers.stream()
                .filter(x -> x.getReleaseDate().equals(localDate))
                .collect(Collectors.toList());
        return offersByReleaseDate;
    }

    public void deleteOfferById(Long id){
        Offer offer = this.findOfferById(id);
        offerRepo.delete(offer);
    }

}
