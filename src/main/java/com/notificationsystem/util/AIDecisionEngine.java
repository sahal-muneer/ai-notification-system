package com.notificationsystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AIDecisionEngine {

    @Autowired
    RestTemplate restTemplate;

    @Value("${groq.api.url}")
    private String API_URL;

    @Value("${groq.api.key}")
    private String API_KEY;

    public Map<String, String> getDecision(String message) {

        System.out.println("=== AIDecisionEngine ===");
        System.out.println("API_URL: " + API_URL);
        System.out.println("API_KEY is empty: " + (API_KEY == null || API_KEY.isBlank()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama-3.3-70b-versatile");
        String prompt = "Classify this message into priority (HIGH/LOW) and channel (EMAIL/SMS/PUSH). "
                + "Message: " + message
                + ". Respond strictly in JSON like {\"priority\":\"HIGH\",\"channel\":\"SMS\"}";


        Map<String, String> msg = new HashMap<>();
        msg.put("role", "user");
        msg.put("content", prompt);


        body.put("messages", new Object[]{msg});

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, request, Map.class);

            System.out.println("Groq Response Status: " + response.getStatusCode());
            System.out.println("Groq Response Body: " + response.getBody());

            Map responseBody = response.getBody();
            if (responseBody == null) {
                System.out.println("ERROR: Response body is null!");
                return fallback();
            }

            List choices = (List) responseBody.get("choices");
            if (choices == null || choices.isEmpty()) {
                System.out.println("ERROR: choices is null or empty!");
                return fallback();
            }

            Map firstChoice = (Map) choices.get(0);
            Map messageMap = (Map) firstChoice.get("message");
            String content = (String) messageMap.get("content");

            System.out.println("AI Content: " + content);

            Map<String, String> result = new HashMap<>();

            if (content.contains("HIGH")) result.put("priority", "HIGH");
            else result.put("priority", "LOW");

            if (content.contains("SMS")) result.put("channel", "SMS");
            else if (content.contains("EMAIL")) result.put("channel", "EMAIL");
            else result.put("channel", "PUSH");

            return result;

        } catch (Exception e) {
            System.out.println("ERROR calling Groq API: " + e.getMessage());
            e.printStackTrace();
            return fallback();
        }
    }

    // fallback if AI fails — so app doesn't crash
    private Map<String, String> fallback() {
        Map<String, String> result = new HashMap<>();
        result.put("priority", "LOW");
        result.put("channel", "EMAIL");
        return result;
    }
}