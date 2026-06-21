package com.autoassess.project.assessment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssessmentHistoryResponse {
    private Long assessmentId;
    private Long quizId;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Integer wrongAnswers;
    private Double scorePercentage;
    private LocalDateTime submittedAt;

}
