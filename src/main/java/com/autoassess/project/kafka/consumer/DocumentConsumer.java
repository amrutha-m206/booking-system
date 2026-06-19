package com.autoassess.project.kafka.consumer;

import com.autoassess.project.document.entity.Document;
import com.autoassess.project.document.repository.DocumentRepository;
import com.autoassess.project.document.service.DocumentService;
import com.autoassess.project.kafka.DocumentEvent;
import com.autoassess.project.kafka.producer.DocumentProducer;
import com.autoassess.project.quiz.service.QuizService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DocumentConsumer {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private QuizService quizService;

    private static final Logger log = LoggerFactory.getLogger(DocumentConsumer.class);

    @KafkaListener(topics="document-uploaded",groupId="auto-assess")
    public void consume(String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentEvent event=objectMapper.readValue(message,DocumentEvent.class); //convert string into a java object of type DocumentEvent

        log.info("Document event received. documentId={}, userId={}",
                event.getDocumentId(), event.getUserId());
        Long documentId=event.getDocumentId();
        Long userId=event.getUserId();



        Document doc=documentRepository.findById(documentId).orElseThrow(()-> new RuntimeException("Document not found"));
        doc=documentService.processDocument(doc);

        quizService.generateQuiz(doc.getId(),userId);

        log.info("Document processing completed and quiz generated");

    }
}
