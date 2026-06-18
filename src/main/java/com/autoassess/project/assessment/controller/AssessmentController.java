package com.autoassess.project.assessment.controller;

import com.autoassess.project.assessment.dto.AssessmentRequest;
import com.autoassess.project.assessment.entity.Assessment;
import com.autoassess.project.assessment.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @PostMapping("/submit")
    public Assessment submit(@RequestBody AssessmentRequest request) throws Exception{
        return assessmentService.submitQuiz(request);
    }
}
