package com.autoassess.project.analytics.service;


import com.autoassess.project.analytics.entity.Analytics;
import com.autoassess.project.analytics.repository.AnalyticsRepository;
import com.autoassess.project.kafka.ScorePublishedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    public void updateAnalytics(Long assessmentId,Long userId,Double Score){

        Analytics analytics =
                analyticsRepository.findByUserId(userId)
                        .orElseGet(() -> {
                            Analytics a = new Analytics();
                            a.setUserId(userId);
                            a.setTotalAttempts(0);
                            a.setTotalScore(0.0);
                            a.setBestScore(0.0);
                            a.setAvgScore(0.0);
                            a.setWorstScore(100.0);

                            return a;
                        });
        analytics.setTotalAttempts(analytics.getTotalAttempts()+1);
        analytics.setTotalScore(analytics.getTotalScore()+Score);
        analytics.setBestScore(Math.max(analytics.getBestScore(), Score));
        analytics.setWorstScore(Math.min(analytics.getWorstScore(), Score));
        analytics.setAvgScore(analytics.getTotalScore() / analytics.getTotalAttempts());

        analyticsRepository.save(analytics);
    }
}
