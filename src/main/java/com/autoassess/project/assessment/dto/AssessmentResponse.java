package com.autoassess.project.assessment.dto;

import lombok.Data;

@Data
public class AssessmentResponse {

    private Long assessmentId;
    private Double score;
    private Integer totalQuestions;
    private Integer correctAnswers;
}
