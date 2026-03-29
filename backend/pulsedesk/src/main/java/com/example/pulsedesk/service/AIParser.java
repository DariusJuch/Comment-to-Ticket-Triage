package com.example.pulsedesk.service;

import com.example.pulsedesk.dto.AIResponse;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class AIParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public AIResponse parse(String raw){
        try {
            if (raw.startsWith("[")){
                JsonNode node = mapper.readTree(raw);
                raw = node.get(0).get("generates_txt").asText();
            }

            int start = raw.indexOf("{");
            int end = raw.lastIndexOf("}");

            if (start != -1 && end != -1){
                raw = raw.substring(start, end + 1);
            }

            return mapper.readValue(raw, AIResponse.class);

        } catch (Exception e){
            AIResponse fallback = new AIResponse();
            fallback.setTicket(false);
            return fallback;
        }
    }
}
