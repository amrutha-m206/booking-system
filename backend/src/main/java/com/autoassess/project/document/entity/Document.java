package com.autoassess.project.document.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    private String fileName;
    private String filePath;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String extractedText;
    private String status; //uploaded,processed
    private LocalDateTime createdAt =LocalDateTime.now();

    //getters and setters



}