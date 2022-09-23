package com.easy_job_search.controller;

import com.easy_job_search.dto_input.AttributeId;
import com.easy_job_search.dto_input.AttributeString;
import com.easy_job_search.dto_output.CandidateResponse;
import com.easy_job_search.dto_output.CompanyResponse;
import com.easy_job_search.entity.*;
import com.easy_job_search.helper.Helper;
import com.easy_job_search.service.CandidateService;
import com.easy_job_search.service.CompanyService;
import com.easy_job_search.service.OfferService;
import com.easy_job_search.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/easy-job-search")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private OfferService offerService;

    @GetMapping("/test")
    public ResponseEntity<String> helloTest() {
        return new ResponseEntity<String>("Hello from myApp", HttpStatus.OK);
    }

    @PostMapping("register-company")
    public ResponseEntity<CompanyResponse> registerCompany(@Valid @RequestBody Company company){

        ResponseEntity<CompanyResponse> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            Company c1 = companyService.createCompany(company);
            response = new ResponseEntity<>(Helper.convertCompanyToCompanyResponse(c1), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PutMapping("update-company")
    public ResponseEntity<CompanyResponse> updateCompany(@RequestBody Company company) {
        ResponseEntity<CompanyResponse> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try {
            Company c1 = (Company) userService.findUserById(company.getId());
            if (company.getFullName() != null) {
                c1.setFullName(company.getFullName());
            } else if (company.getEmail() != null) {
                c1.setEmail(company.getEmail());
            } else if (company.getPassword() != null) {
                c1.setPassword(company.getPassword());
            } else if (company.getAddress() != null) {
                c1.setAddress(company.getAddress());
            }
            companyService.updateCompany(c1);
            response = new ResponseEntity<>(Helper.convertCompanyToCompanyResponse(c1), HttpStatus.OK);
        } catch (ClassCastException | IllegalArgumentException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @GetMapping("companies")
    public ResponseEntity<List<CompanyResponse>> getCompaniesList(){
        List<Company> companies = companyService.findAllCompany();
        return new ResponseEntity<>(Helper.convertListCompanyToListCompanyResponse(companies),
                HttpStatus.OK);
    }

    @GetMapping("search-companyById")
    public ResponseEntity<CompanyResponse> getCompanyById(@RequestBody AttributeId idCompany){
        ResponseEntity<CompanyResponse> response = new ResponseEntity<CompanyResponse>(HttpStatus.NO_CONTENT);
        try{
            Company company = (Company) userService.findUserById(idCompany.getId());
            response = new ResponseEntity<>(Helper.convertCompanyToCompanyResponse(company),
                    HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException cce){
            cce.printStackTrace();
        }
        return response;
    }

    @GetMapping("search-companyByEmail")
    public ResponseEntity<CompanyResponse> getCompanyByEmail(@RequestBody AttributeString email){
        ResponseEntity<CompanyResponse> response = new ResponseEntity<CompanyResponse>(HttpStatus.NO_CONTENT);
        try{
            Company company = (Company) userService.findUserByEmail(email.getName());
            response = new ResponseEntity<>(Helper.convertCompanyToCompanyResponse(company), HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException cce){
            cce.printStackTrace();
        }
        return response;
    }

    @GetMapping("search-companyByFullName")
    public ResponseEntity<List<CompanyResponse>> getCompanyByFullName(@RequestBody AttributeString fullName){

        ResponseEntity<List<CompanyResponse>> response = new ResponseEntity<List<CompanyResponse>>(HttpStatus.NO_CONTENT);
        try{
            List<Company> companies = companyService.findCompanyByFullName(fullName.getName());
            response = new ResponseEntity<>(Helper.convertListCompanyToListCompanyResponse(companies), HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException cce){
            cce.printStackTrace();
        }
        return response;

    }

    //----------------------------------CandidateController---------------------------------------

    @PostMapping("register-candidate")
    public ResponseEntity<CandidateResponse> registerCandidate(@Valid @RequestBody Candidate candidate){
        ResponseEntity<CandidateResponse> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            Candidate c1 = candidateService.createCandidate(candidate);
            response = new ResponseEntity<>(Helper.convertCandidateToCandidateResponse(c1), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PutMapping("update-candidate-personal-data")
    public ResponseEntity<CandidateResponse> updateCandidatePersonalData(@RequestBody Candidate candidate) {
        ResponseEntity<CandidateResponse> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try {
            Candidate c1 = (Candidate) userService.findUserById(candidate.getId());
            if (candidate.getFullName() != null) {
                c1.setFullName(candidate.getFullName());
            } else if (candidate.getEmail() != null) {
                c1.setEmail(candidate.getEmail());
            } else if (candidate.getPassword() != null) {
                c1.setPassword(candidate.getPassword());
            } else if (candidate.getAddress() != null) {
                c1.setAddress(candidate.getAddress());
            }
            candidateService.updateCandidate(c1);
            response = new ResponseEntity<>(Helper.convertCandidateToCandidateResponse(c1), HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @PutMapping("add-education/{idCandidate}")
    public ResponseEntity<CandidateResponse> updateCandidateEducation(@PathVariable("idCandidate") long idCandidate,
                                                                      @RequestBody Education education){
        Candidate candidate = (Candidate) userService.findUserById(idCandidate);
        if(candidate != null){
            candidate.getEducationList().add(education);
            candidateService.updateCandidate(candidate);
        }
        return new ResponseEntity<>(Helper.convertCandidateToCandidateResponse(candidate), HttpStatus.OK);
    }

    @PutMapping("add-workExperience/{idCandidate}")
    public ResponseEntity<CandidateResponse> updateCandidateEducation(@PathVariable("idCandidate") long idCandidate,
                                                                      @RequestBody WorkExperience workExperience){
        Candidate candidate = (Candidate) userService.findUserById(idCandidate);
        if(candidate != null){
            candidate.getWorkExperienceList().add(workExperience);
            candidateService.updateCandidate(candidate);
        }
        return new ResponseEntity<>(Helper.convertCandidateToCandidateResponse(candidate), HttpStatus.OK);
    }

    @GetMapping("candidates")
    public ResponseEntity<List<CandidateResponse>> getCandidatesList(){
        List<Candidate> candidates = candidateService.findAllCandidate();
        return new ResponseEntity<>(Helper.convertListCandidateToListCandidateResponse(candidates), HttpStatus.OK);
    }

    @GetMapping("search-candidateById")
    public ResponseEntity<CandidateResponse> getCandidateById(@RequestBody AttributeId idCandidate){
        ResponseEntity<CandidateResponse> response = new ResponseEntity<CandidateResponse>(HttpStatus.NO_CONTENT);
        try{
            Candidate candidate = (Candidate) userService.findUserById(idCandidate.getId());
            response =  new ResponseEntity<>(Helper.convertCandidateToCandidateResponse(candidate),
                    HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException ex){
            ex.printStackTrace();
        }
        return response;

    }

    @GetMapping("search-candidateByEmail")
    public ResponseEntity<CandidateResponse> getCandidateByEmail(@RequestBody AttributeString email){
        ResponseEntity<CandidateResponse> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try{
            Candidate candidate = (Candidate) userService.findUserByEmail(email.getName());
            response =  new ResponseEntity<>(Helper.convertCandidateToCandidateResponse(candidate),
                    HttpStatus.OK);
        }catch (ClassCastException | IllegalArgumentException ex){
            ex.printStackTrace();
        }
        return response;
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity deleteUserById (@PathVariable("id") long idUser){

        ResponseEntity response = new ResponseEntity(HttpStatus.NO_CONTENT);
        try{
            userService.deleteUserById(idUser);
            return ResponseEntity.ok("User removed successfully");
        }catch (EmptyResultDataAccessException ex){
            ex.printStackTrace();
        }
        return response;
    }

}
