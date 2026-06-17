package com.autoassess.project.quiz.controller;


import com.autoassess.project.quiz.entity.Quiz;
import com.autoassess.project.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/generate")
    public Quiz generate(@RequestParam Long documentId){
        return quizService.generateQuiz(documentId);
    }

}
