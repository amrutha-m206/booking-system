package com.autoassess.project.assessment.controller;

import com.autoassess.project.assessment.dto.AssessmentRequest;
import com.autoassess.project.assessment.entity.Assessment;
import com.autoassess.project.assessment.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @PostMapping("/submit")
    public Assessment submit(@RequestBody AssessmentRequest request) throws Exception{
        return assessmentService.submitQuiz(request);
    }

    @GetMapping("/history")
    public List<Assessment> getHistory(){
        return assessmentService.getHistory();
    }

    @GetMapping("/analytics")
    public Map<String,Object> getAnalytics(){
        return assessmentService.getAnalytics();
    }


}
