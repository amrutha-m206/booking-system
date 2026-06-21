package com.autoassess.project.kafka.producer;

import com.autoassess.project.document.service.DocumentService;
import com.autoassess.project.kafka.ScorePublishedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScoreProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate; //Spring is already creating a KafkaTemplate bean somewhere in the application context(kafka config )
    //It is injecting that existing object into your class
    private static final String TOPIC="score-published";
    private static final Logger log = LoggerFactory.getLogger(ScoreProducer.class);

    public void publishScore(Long assessmentId,Long userId,double score) throws JsonProcessingException {
        ScorePublishedEvent event=new ScorePublishedEvent();  //can be done in service class also

        event.setAssessmentId(assessmentId);
        event.setUserId(userId);
        event.setScore(score);

        ObjectMapper mapper=new ObjectMapper();
        String message=mapper.writeValueAsString(event);
        log.info("Publishing score event: {}", event);
        kafkaTemplate.send(TOPIC,message);

    }
}

