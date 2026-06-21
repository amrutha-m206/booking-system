package com.autoassess.project.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssessmentRequest {
    private Long quizId;
    private List<String> answers;
}
