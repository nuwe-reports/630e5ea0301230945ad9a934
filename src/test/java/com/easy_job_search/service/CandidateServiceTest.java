package com.easy_job_search.service;

import com.easy_job_search.entity.*;
import com.easy_job_search.repository.CandidateRepo;
import com.easy_job_search.utility.JobType;
import com.easy_job_search.utility.ModalityJob;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CandidateServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private CandidateRepo candidateRepo;
    @Mock
    private OfferService offerService;
    @InjectMocks
    private CandidateService candidateService;
    private Candidate candidate;
    private Offer offer;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Address address = new Address();
        address.setId(1);
        address.setStreet("planeta azul");
        address.setNumber(1);
        address.setFloor("bajo");
        address.setDoor("1");
        address.setCity("azulea");
        address.setCountry("azul resplandeciente");
        address.setPlanet("Tierra");

        Education education = new Education();
        education.setId(1);
        education.setTypeOfDegree("Ingeniero");
        education.setEducationalInstitution("Universidad del Sistema Solar");
        education.setStartDate(LocalDate.of(2020, 2, 15));
        education.setFinishDate(LocalDate.of(2020, 12, 1));

        WorkExperience workExperience = new WorkExperience();
        workExperience.setId(1);
        workExperience.setPosition("ingeniero de planetas");
        workExperience.setStartDate(LocalDate.of(2022, 01, 15));
        workExperience.setDescription("estudio en profundidad de la composición planetaria");

        List<Education> educationList = new ArrayList<>();
        educationList.add(education);

        List<WorkExperience> workExperienceList = new ArrayList<>();
        workExperienceList.add(workExperience);

        candidate = new Candidate();
        candidate.setId(1);
        candidate.setEmail("candidate@email.com");
        candidate.setFullName("Jose Perez");
        candidate.setPassword("prueba1234");
        candidate.setAddress(address);
        candidate.setEducationList(educationList);
        candidate.setWorkExperienceList(workExperienceList);
        candidate.setProfile("ingeniero de planetas, especializado en mineria planetaria");
        candidate.setOffersRegisterIds(new ArrayList<>());

        Company company = new Company();
        company.setId(2);
        company.setFullName("Empresa interestelar");
        company.setEmail("interestelar@email.com");
        company.setPassword("prueba1234");

        String[] skills = {"tecnologia", "tecnicas de supervivencia"};
        offer = new Offer();
        offer.setId(1);
        offer.setPosition("planetologo");
        offer.setJobType(JobType.FULL_TIME);
        offer.setModalityJob(ModalityJob.PRESENTIAL);
        offer.setLocation("sistema solar");
        offer.setSector("exploracion y mineria");
        offer.setDescription("descubir nuevos entornos y minerales en diferentes planetas del sistema solar");
        offer.setSkills(skills);
        offer.setOwner(company);
        offer.setCandidates(new ArrayList<>());

    }

    @Test
    void createCandidate() {
        when(candidateRepo.save(any(Candidate.class))).thenReturn(candidate);
        assertEquals(candidateService.createCandidate(candidate), candidate);
    }

    @Test
    void updateCandidate() {
        when(candidateRepo.save(any(Candidate.class))).thenReturn(candidate);
        assertEquals(candidateService.updateCandidate(candidate), candidate);
    }

    @Test
    void findAllCandidate() {
        when(candidateRepo.findAll()).thenReturn(Arrays.asList(candidate));
        assertEquals(candidateService.findAllCandidate(), Arrays.asList(candidate));
    }

    @Test
    void registerToOffer() {
        when(((Candidate) userService.findUserById(candidate.getId()))).thenReturn(Optional.of(candidate).orElse(null));
        when(offerService.findOfferById(offer.getId())).thenReturn(Optional.of(offer).orElse(null));
        when(candidateService.updateCandidate(any(Candidate.class))).thenReturn(candidate);
        when(offerService.updateOffer(any(Offer.class))).thenReturn(offer);
        assertEquals(candidateService.registerToOffer(candidate.getId(), offer.getId()), offer);
    }
}