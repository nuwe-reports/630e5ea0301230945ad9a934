package com.easy_job_search.helper;

import com.easy_job_search.dto_output.*;
import com.easy_job_search.entity.Candidate;
import com.easy_job_search.entity.Company;
import com.easy_job_search.entity.Offer;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static OfferResponse convertOfferToOfferResponse(Offer offer){
        OfferResponse offerResponse = new OfferResponse();
        BeanUtils.copyProperties(offer, offerResponse);
        CompanyShort companyShort = new CompanyShort();
        BeanUtils.copyProperties(offer.getOwner(), companyShort);
        offerResponse.setOwner(companyShort);
        return offerResponse;
    }

    public static CompanyResponse convertCompanyToCompanyResponse(Company company){
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        return companyResponse;
    }

    public static CandidateResponse convertCandidateToCandidateResponse(Candidate candidate){
        CandidateResponse candidateResponse = new CandidateResponse();
        BeanUtils.copyProperties(candidate, candidateResponse);
        return candidateResponse;
    }

    public static List<CandidateResponse> convertListCandidateToListCandidateResponse(List<Candidate> candidates){
        List<CandidateResponse> candidateResponseList = new ArrayList<>();
        candidates.stream().forEach(candidate -> {
            CandidateResponse candidateResponse = new CandidateResponse();
            BeanUtils.copyProperties(candidate, candidateResponse);
            candidateResponseList.add(candidateResponse);
        });
        return candidateResponseList;
    }

    public static List<CompanyResponse> convertListCompanyToListCompanyResponse(List<Company> companies){
        List<CompanyResponse> companyResponseList = new ArrayList<>();
        companies.stream().forEach(company -> {
            CompanyResponse companyResponse = new CompanyResponse();
            BeanUtils.copyProperties(company, companyResponse);
            companyResponseList.add(companyResponse);
        });
        return companyResponseList;
    }

    public static List<OfferResponse> convertListOfferToListOfferResponse(List<Offer> offers){
        List<OfferResponse> offerResponseList = new ArrayList<>();
        offers.stream().forEach(offer -> {
            OfferResponse offerResponse = new OfferResponse();
            BeanUtils.copyProperties(offer, offerResponse);
            CompanyShort owner = new CompanyShort();
            BeanUtils.copyProperties(offer.getOwner(), owner);
            offerResponse.setOwner(owner);
            offerResponseList.add(offerResponse);
        });
        return offerResponseList;
    }

    public static OfferTotalRegistered convertOfferToOfferTotalRegistered (Offer offer){
        OfferTotalRegistered offerTotalRegistered = new OfferTotalRegistered();
        BeanUtils.copyProperties(offer, offerTotalRegistered);
        CompanyShort companyShort = new CompanyShort();
        BeanUtils.copyProperties(offer.getOwner(), companyShort);
        offerTotalRegistered.setOwner(companyShort);
        offerTotalRegistered.setTotalRegistered(offer.getCandidates().size());
        return offerTotalRegistered;
    }

    public static List<OfferTotalRegistered> convertListOfferToOfferTotalRegistered(List<Offer> offers){
        List<OfferTotalRegistered> listRegistered = new ArrayList<>();
        offers.stream().forEach(offer -> {
            OfferTotalRegistered offerTotalRegistered = new OfferTotalRegistered();
            BeanUtils.copyProperties(offer, offerTotalRegistered);
            CompanyShort owner = new CompanyShort();
            BeanUtils.copyProperties(offer.getOwner(), owner);
            offerTotalRegistered.setOwner(owner);
            offerTotalRegistered.setTotalRegistered(offer.getCandidates().size());
            listRegistered.add(offerTotalRegistered);
        });
        return listRegistered;
    }


}
