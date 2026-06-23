package com.autoassess.project.assessment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AssessmentRequest {

    @NotNull
    private Long quizId;

    private List<String> answers;
}
