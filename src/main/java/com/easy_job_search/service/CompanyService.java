package com.easy_job_search.service;

import com.easy_job_search.entity.Company;
import com.easy_job_search.entity.Offer;
import com.easy_job_search.repository.CompanyRepo;
import com.easy_job_search.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private OfferService offerService;
    @Autowired
    private EmailService emailService;

    public Company createCompany(Company company) {
        return companyRepo.save(company);
    }

    public Company updateCompany(Company company){
        return companyRepo.save(company);
    }

    public List<Company> findAllCompany(){
        return (List<Company>) companyRepo.findAll();
    }

    public List<Company> findCompanyByFullName(String fullName){
        Optional<List<Company>> op = companyRepo.findCompanyByFullName(fullName);
        return op.orElse(null);

    }

    public Offer addOffer(long companyId, Offer offer){
        Company company = (Company) userService.findUserById(companyId);
        Offer o1 = offerService.createOffer(offer);
        o1.setOwner(company);
        emailService.sendEmailToAllSubs(offer);
        return offerService.updateOffer(o1);
    }




}
