package com.autoassess.project.analytics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Analytics {

    @Id
    private Long userId;

    private Integer totalAttempts;
    private Double totalScore;
    private Double bestScore;
    private Double worstScore;
    private Double avgScore;

}
