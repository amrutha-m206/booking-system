package com.autoassess.project.quiz.controller;


import com.autoassess.project.quiz.entity.Quiz;
import com.autoassess.project.quiz.service.QuizService;
import com.autoassess.project.security.AuthUtil;
import com.autoassess.project.user.entity.User;
import com.autoassess.project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/generate")
    public Quiz generate(@RequestParam Long documentId){
        String email= authUtil.getCurrentUserEmail();
        User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        return quizService.generateQuiz(documentId,user.getId());
    }



}
