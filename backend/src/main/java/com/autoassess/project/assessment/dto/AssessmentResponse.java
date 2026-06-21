package com.autoassess.project.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssessmentResponse {

    private Long assessmentId;
    private Double score;
    private Integer totalQuestions;
    private Integer correctAnswers;

    private List<QuestionResultDto> results;
}
