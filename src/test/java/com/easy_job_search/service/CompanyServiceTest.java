package com.easy_job_search.service;

import com.easy_job_search.entity.*;
import com.easy_job_search.repository.CompanyRepo;
import com.easy_job_search.repository.UserRepo;
import com.easy_job_search.utility.JobType;
import com.easy_job_search.utility.ModalityJob;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class CompanyServiceTest {

    @Mock
    UserRepo userRepo;
    @Mock
    private UserService userService;
    @Mock
    private CompanyRepo companyRepo;
    @Mock
    private OfferService offerService;
    @Mock
    private EmailService emailService;
    @InjectMocks
    private CompanyService companyService;
    private Company company;
    private Offer offer;

    @BeforeEach
    void setUp() {
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


        Candidate candidate = new Candidate();
        candidate.setId(1);
        candidate.setEmail("candidate@email.com");
        candidate.setFullName("Jose Perez");
        candidate.setPassword("prueba1234");
        candidate.setAddress(address);
        candidate.setProfile("ingeniero de planetas, especializado en mineria planetaria");
        candidate.setOffersRegisterIds(new ArrayList<>());

        company = new Company();
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
    void createCompany() {
        when(companyRepo.save(any(Company.class))).thenReturn(company);
        assertEquals(companyService.createCompany(company), company);
    }

    @Test
    void updateCompany() {
        when(companyRepo.save(any(Company.class))).thenReturn(company);
        assertEquals(companyService.updateCompany(company), company);
    }

    @Test
    void findAllCompany() {
        when(companyRepo.findAll()).thenReturn(Arrays.asList(company));
        assertEquals(companyService.findAllCompany(), Arrays.asList(company));
    }

    @Test
    void findCompanyByFullName() {
        when(companyRepo.findCompanyByFullName(company.getFullName())).thenReturn(Optional.of(Arrays.asList(company)));
        assertEquals(companyService.findCompanyByFullName("Empresa interestelar"), Arrays.asList(company));
    }

    @Test
    void addOffer() {
        when((Company) userService.findUserById(company.getId())).thenReturn(Optional.of(company).orElse(null));
        when(offerService.createOffer(any(Offer.class))).thenReturn(offer);
        when(offerService.updateOffer(any(Offer.class))).thenReturn(offer);
        assertEquals(companyService.addOffer(1, offer), offer);
    }
}