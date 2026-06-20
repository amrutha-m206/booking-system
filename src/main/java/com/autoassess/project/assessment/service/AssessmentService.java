package com.autoassess.project.assessment.service;

import com.autoassess.project.assessment.dto.AssessmentRequest;
import com.autoassess.project.assessment.dto.AssessmentResponse;
import com.autoassess.project.assessment.entity.Assessment;
import com.autoassess.project.assessment.repository.AssessmentRepository;
import com.autoassess.project.kafka.producer.ScoreProducer;
import com.autoassess.project.quiz.entity.Quiz;
import com.autoassess.project.quiz.repository.QuizRepository;
import com.autoassess.project.security.AuthUtil;
import com.autoassess.project.user.entity.User;
import com.autoassess.project.user.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreProducer scoreProducer;

    private final ObjectMapper mapper=new ObjectMapper();

    private static final Logger log=LoggerFactory.getLogger(AssessmentService.class);

    public AssessmentResponse submitQuiz(AssessmentRequest request) throws Exception{

        String email=authUtil.getCurrentUserEmail();
        User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        log.info("Submitting quiz for userId: {}, quizId: {}", user.getId(), request.getQuizId());
        Quiz quiz=quizRepository.findById(request.getQuizId()).orElseThrow(()-> new RuntimeException("Quiz not found"));

        JsonNode quizArray=mapper.readTree(quiz.getQuestions());
        log.info("Quiz loaded successfully. Total questions: {}", quizArray.size());

        int correct = 0;

        int totalQuestions=quizArray.size();

        for(int i=0;i<quizArray.size();i++){
            JsonNode q=quizArray.get(i);

            String correctAnswer=q.get("answer").asText();

            String userAnswer=request.getAnswers().get(i);
            log.info("Q{} -> correct: {}, user: {}", i + 1, correctAnswer, userAnswer);
            if(correctAnswer.equalsIgnoreCase(userAnswer)){
                correct++;
            }
        }

        int wrong = totalQuestions - correct;

        double scorePercentage = ((double) correct / totalQuestions) * 100;

        log.info("Final Score: {}/{} ({}%)", correct, totalQuestions, scorePercentage);

        Assessment assessment=new Assessment();
        assessment.setUserId(user.getId());
        assessment.setQuizId(request.getQuizId());
        assessment.setTotalQuestions((totalQuestions));
        assessment.setCorrectAnswers(correct);
        assessment.setWrongAnswers(wrong);
        assessment.setScorePercentage(scorePercentage);
        assessment.setSubmittedAt(LocalDateTime.now());

        log.info("Saving assessment...");

        Assessment saved= assessmentRepository.save(assessment);
        log.info("Assessment saved successfully with id: {}", saved.getId());

        scoreProducer.publishScore(saved.getId(),saved.getUserId(),saved.getScorePercentage());

        AssessmentResponse response=new AssessmentResponse();
        response.setAssessmentId(saved.getId());
        response.setScore(saved.getScorePercentage());
        response.setTotalQuestions(saved.getTotalQuestions());
        response.setCorrectAnswers(saved.getCorrectAnswers());

        return response;

    }

    public List<Assessment> getHistory(){
        String email=authUtil.getCurrentUserEmail();

        User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        return assessmentRepository.findByUserId(user.getId());
    }



}
