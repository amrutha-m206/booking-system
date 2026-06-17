package com.autoassess.project.quiz.service;

import com.autoassess.project.document.entity.Document;
import com.autoassess.project.document.repository.DocumentRepository;
import com.autoassess.project.quiz.entity.Quiz;
import com.autoassess.project.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private DocumentRepository documentRepository;


    public Quiz generateQuiz(Long documentId){

        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        String text = doc.getExtractedText();
        String prompt=buildPrompt(text);
        String aiResponse=callLLM(prompt);

        Quiz quiz=new Quiz();
        quiz.setDocumentId(documentId);
        quiz.setQuestions(aiResponse);

        return quizRepository.save(quiz);
    }

    private String buildPrompt(String text){
        return "Generate 5 MCQ questions from this text:\n"+text;
    }

    private String callLLM(String prompt){
        //placeholder
        return "[{\"question\":\"What is AI?\",\"options\":[\"A\",\"B\",\"C\",\"D\"],\"answer\":\"A\"}]";
    }

}
