package com.autoassess.project.assessment.service;

import com.autoassess.project.assessment.dto.AssessmentRequest;
import com.autoassess.project.assessment.entity.Assessment;
import com.autoassess.project.assessment.repository.AssessmentRepository;
import com.autoassess.project.quiz.entity.Quiz;
import com.autoassess.project.quiz.repository.QuizRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private QuizRepository quizRepository;

    private final ObjectMapper mapper=new ObjectMapper();

    private static final Logger log=LoggerFactory.getLogger(AssessmentService.class);

    public Assessment submitQuiz(AssessmentRequest request) throws Exception{


        log.info("Submitting quiz for userId: {}, quizId: {}", request.getUserId(), request.getQuizId());
        Quiz quiz=quizRepository.findById(request.getQuizId()).orElseThrow(()-> new RuntimeException("Quiz not found"));

        JsonNode quizArray=mapper.readTree(quiz.getQuestions());
        log.info("Quiz loaded successfully. Total questions: {}", quizArray.size());

        int score=0;
        int totalQuestions=quizArray.size();

        for(int i=0;i<quizArray.size();i++){
            JsonNode q=quizArray.get(i);

            String correctAnswer=q.get("answer").asText();

            String userAnswer=request.getAnswers().get(i);
            log.info("Q{} -> correct: {}, user: {}", i + 1, correctAnswer, userAnswer);
            if(correctAnswer.equalsIgnoreCase(userAnswer)){
                score++;
            }
        }
        log.info("Final Score: {}/{}", score, totalQuestions);

        Assessment assessment=new Assessment();
        assessment.setUserId(request.getUserId());
        assessment.setQuizId(request.getQuizId());
        assessment.setScore(score);
        assessment.setTotalQuestions((totalQuestions));
        assessment.setSubmittedAt(LocalDateTime.now());

        log.info("Saving assessment...");

        Assessment saved= assessmentRepository.save(assessment);
        log.info("Assessment saved successfully with id: {}", saved.getId());
        return saved;

    }

}
