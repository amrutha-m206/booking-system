package com.autoassess.project.kafka.consumer;

import com.autoassess.project.kafka.ScorePublishedEvent;
import com.autoassess.project.notification.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics="score-published",groupId="notification-group")
    public void consume(String message) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        ScorePublishedEvent event=mapper.readValue(message,ScorePublishedEvent.class);

        log.info("Score published event received by Notification Consumer. assessmentId={}, userId={},score={}",
                event.getAssessmentId(), event.getUserId(),event.getScore());

        notificationService.createNotification(event.getUserId(), "Assessment completed.Score: "+event.getScore()+"%");
        log.info("Notification created by notification service");

    }
}
