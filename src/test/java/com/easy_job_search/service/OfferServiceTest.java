package com.easy_job_search.service;

import com.easy_job_search.entity.*;
import com.easy_job_search.repository.OfferRepo;
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
import static org.mockito.Mockito.when;

class OfferServiceTest {

    @Mock
    private OfferRepo offerRepo;
    @InjectMocks
    private OfferService offerService;
    private Offer offer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Company company = new Company();
        company.setId(1);
        company.setFullName("Empresa interestelar");
        company.setEmail("interestelar@email.com");
        company.setPassword("prueba1234");

        String[] skills = {"tecnologia", "tecnicas de supervivencia, geografia"};
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
    void createOffer() {
        when(offerRepo.save(any(Offer.class))).thenReturn(offer);
        assertEquals(offerService.createOffer(offer), offer);
    }

    @Test
    void updateOffer() {
        when(offerRepo.save(any(Offer.class))).thenReturn(offer);
        assertEquals(offerService.updateOffer(offer), offer);
    }

    @Test
    void findAllOffer() {
        when(offerRepo.findAll()).thenReturn(Arrays.asList(offer));
        assertEquals(offerService.findAllOffer(), Arrays.asList(offer));
    }

    @Test
    void findOfferById() {
        when(offerRepo.findById(offer.getId())).thenReturn(Optional.of(offer));
        assertEquals(offerService.findOfferById(1), offer);
    }

    @Test
    void findOfferByPosition() {
        when(offerService.findAllOffer()).thenReturn(Arrays.asList(offer));
        assertEquals(offerService.findOfferByPosition("planetologo"), Arrays.asList(offer));
    }

    @Test
    void findOfferByJobType() {
        when(offerService.findAllOffer()).thenReturn(Arrays.asList(offer));
        assertEquals(offerService.findOfferByJobType(JobType.FULL_TIME), Arrays.asList(offer));
    }

    @Test
    void findOfferByModality() {
        when(offerService.findAllOffer()).thenReturn(Arrays.asList(offer));
        assertEquals(offerService.findOfferByModality(ModalityJob.PRESENTIAL), Arrays.asList(offer));
    }

    @Test
    void findOfferByLocation() {
        when(offerService.findAllOffer()).thenReturn(Arrays.asList(offer));
        assertEquals(offerService.findOfferByLocation("sistema solar"), Arrays.asList(offer));
    }

    @Test
    void findOfferBySector() {
        when(offerService.findAllOffer()).thenReturn(Arrays.asList(offer));
        assertEquals(offerService.findOfferBySector("exploracion y mineria"), Arrays.asList(offer));
    }

    @Test
    void findOfferBySkill() {
        when(offerService.findAllOffer()).thenReturn(Arrays.asList(offer));
        assertEquals(offerService.findOfferBySkill("geografia"), Arrays.asList(offer));
    }

    @Test
    void findOfferByOwnerId() {
        when(offerService.findAllOffer()).thenReturn(Arrays.asList(offer));
        assertEquals(offerService.findOfferByOwnerId(1), Arrays.asList(offer));
    }

    @Test
    void findOfferByOwnerName() {
        when(offerService.findAllOffer()).thenReturn(Arrays.asList(offer));
        assertEquals(offerService.findOfferByOwnerName("Empresa interestelar"), Arrays.asList(offer));
    }
}