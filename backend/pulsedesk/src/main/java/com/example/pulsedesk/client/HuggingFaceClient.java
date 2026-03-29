package com.example.pulsedesk.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HuggingFaceClient {

    @Value("${huggingface.api-key}")
    private String apiKey;

    @Value("${huggingface.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public String classify(String text) {

        String prompt = """
        You are a strict backend service.

        Return ONLY JSON:
        {
          "isTicket": true/false,
          "title": "...",
          "category": "bug|feature|billing|account|other",
          "priority": "low|medium|high",
          "summary": "..."
        }

        Comment:
        "%s"
        """.formatted(text);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{\"inputs\": \"" + prompt.replace("\"", "\\\"") + "\"}";

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(apiUrl, request, String.class);
    }
}
