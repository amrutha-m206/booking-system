package com.autoassess.project.quiz.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long documentId;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String questions;

    private LocalDateTime createdAt= LocalDateTime.now();

}
