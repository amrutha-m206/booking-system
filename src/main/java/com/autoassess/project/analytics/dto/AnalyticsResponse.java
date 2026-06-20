package com.autoassess.project.analytics.dto;

import lombok.Data;

@Data
public class AnalyticsResponse {

    private Integer totalAttempts;
    private Double avgScore;
    private Double bestScore;
    private Double worstScore;
}
