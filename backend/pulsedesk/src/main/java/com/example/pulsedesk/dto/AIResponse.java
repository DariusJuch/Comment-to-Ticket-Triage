package com.example.pulsedesk.dto;

import lombok.Data;

@Data
public class AIResponse {
    private boolean isTicket;
    private String title;
    private String category;
    private String priority;
    private String summary;
}
