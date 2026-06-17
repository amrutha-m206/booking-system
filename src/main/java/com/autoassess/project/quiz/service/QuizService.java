package com.autoassess.project.quiz.service;

import com.autoassess.project.document.entity.Document;
import com.autoassess.project.document.repository.DocumentRepository;
import com.autoassess.project.quiz.client.GroqClient;
import com.autoassess.project.quiz.entity.Quiz;
import com.autoassess.project.quiz.repository.QuizRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private GroqClient groqClient;
    private static final Logger log =LoggerFactory.getLogger(QuizService.class);
    public Quiz generateQuiz(Long documentId){

        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        String text = doc.getExtractedText();
        String prompt=buildPrompt(text);
        log.info("Generating quiz for documentId {}", documentId);
        String aiResponse=callLLM(prompt);
        log.info("Quiz generated successfully");
        Quiz quiz=new Quiz();
        quiz.setDocumentId(documentId);
        log.info("LLM Response: {}", aiResponse);
        quiz.setQuestions(aiResponse);

        return quizRepository.save(quiz);
    }

    private String buildPrompt(String text){

            return """
            Generate exactly 5 MCQs from the following content.
            
            Return ONLY valid JSON.
            
            Format:
            
            [
             {
               "question":"...",
               "options":["A","B","C","D"],
               "answer":"..."
             }
            ]
            
            Content:
            """ + text;
    }

    private String callLLM(String prompt){

        return groqClient.generateQuiz(prompt);
    }

}
