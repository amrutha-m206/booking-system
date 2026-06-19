package com.autoassess.project.kafka;

import lombok.Data;

@Data
public class DocumentEvent {
    private Long documentId;
    private Long userId;
}
