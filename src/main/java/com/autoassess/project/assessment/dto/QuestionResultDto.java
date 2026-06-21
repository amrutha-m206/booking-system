package com.autoassess.project.assessment.dto;

import lombok.Data;

@Data
public class QuestionResultDto {

    private String question;
    private String userAnswer;
    private String correctAnswer;
    private boolean correct;

}
