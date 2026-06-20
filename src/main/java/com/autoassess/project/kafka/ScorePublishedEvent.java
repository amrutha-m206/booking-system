package com.autoassess.project.kafka;

import lombok.Data;

@Data
public class ScorePublishedEvent {

    private Long assessmentId;
    private Long userId;
    private Double score;
}
