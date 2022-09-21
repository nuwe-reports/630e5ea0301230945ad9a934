package com.easy_job_search.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String position;
    private LocalDate startDate;
    private LocalDate finishDate;
    private boolean currentJob;
    private String description;

    public WorkExperience(){
        if(finishDate == null){
            this.currentJob = true;
        }
    }

}
