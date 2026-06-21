package com.autoassess.project.document.dto;

import lombok.Data;

@Data
public class DocumentUploadResponse {
    private Long id;
    private String fileName;
    private String status;
}
