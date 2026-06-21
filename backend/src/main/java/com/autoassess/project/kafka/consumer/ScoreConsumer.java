package com.autoassess.project.kafka.consumer;

import com.autoassess.project.analytics.service.AnalyticsService;
import com.autoassess.project.document.entity.Document;
import com.autoassess.project.kafka.DocumentEvent;
import com.autoassess.project.kafka.ScorePublishedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ScoreConsumer {

    @Autowired
    private AnalyticsService analyticsService;

    @KafkaListener(topics="score-published",groupId = "analytics-group")
    public void consume(String message) throws JsonProcessingException {

        ObjectMapper mapper=new ObjectMapper();
        ScorePublishedEvent event=mapper.readValue(message,ScorePublishedEvent.class);
        log.info("Score published event received by Analytics Consumer. assessmentId={}, userId={},score={}",
                event.getAssessmentId(), event.getUserId(),event.getScore());

        analyticsService.updateAnalytics(event.getAssessmentId(), event.getUserId(),event.getScore());
        log.info("Analytics created/updated by analytics service");

    }

}
