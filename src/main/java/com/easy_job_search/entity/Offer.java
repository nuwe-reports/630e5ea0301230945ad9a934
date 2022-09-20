package com.easy_job_search.entity;

import com.easy_job_search.utility.JobType;
import com.easy_job_search.utility.ModalityJob;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String position;
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    @Enumerated(EnumType.STRING)
    private ModalityJob modalityJob;
    private String location;
    private String sector;
    private String description;
    private String[] skills;
    private LocalDate releaseDate;
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "offer_company", joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "company_user_id"))
    private Company owner;
    @ElementCollection
    private List<Candidate> candidates;


    public Offer(){
        this.releaseDate = LocalDate.now();
    }
}
