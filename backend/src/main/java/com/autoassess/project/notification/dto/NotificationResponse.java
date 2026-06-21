package com.autoassess.project.notification.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {

    private Long id;
    private String message;
    private LocalDateTime createdAt;
}
