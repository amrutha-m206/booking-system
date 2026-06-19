package com.autoassess.project.kafka.producer;


import com.autoassess.project.kafka.DocumentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class DocumentProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private static final String TOPIC="document-uploaded";

    private static final Logger log = LoggerFactory.getLogger(DocumentProducer.class);

    public void sendDocumentEvent(DocumentEvent event){
        try{
            ObjectMapper mapper=new ObjectMapper();
            String message=mapper.writeValueAsString(event);

            log.info("Sending document upload event. documentId={}, userId={}",
                    event.getDocumentId(), event.getUserId());

            kafkaTemplate.send(TOPIC,message);

        } catch (Exception e) {
            log.error("Failed to send document event", e);
            throw new RuntimeException("Failed to send Kafka event", e);
        }
    }
}
