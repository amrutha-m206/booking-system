package com.autoassess.project.quiz.client;

import com.autoassess.project.quiz.dto.GroqRequest;
import com.autoassess.project.quiz.dto.GroqResponse;
import com.autoassess.project.quiz.dto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import java.util.List;

@Component
public class GroqClient {

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.url}")
    private String apiUrl;

    public String generateQuiz(String prompt) {

        RestTemplate restTemplate = new RestTemplate();

        GroqRequest request = new GroqRequest();
        request.setModel("llama-3.3-70b-versatile");
        request.setMessages(
                List.of(new Message("user", prompt))
        );


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GroqRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<GroqResponse> response =
                restTemplate.exchange(
                        apiUrl,
                        HttpMethod.POST,
                        entity,
                        GroqResponse.class
                );

        return response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }

}
