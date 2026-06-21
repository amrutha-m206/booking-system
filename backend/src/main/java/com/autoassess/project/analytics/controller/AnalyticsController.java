package com.autoassess.project.analytics.controller;


import com.autoassess.project.analytics.dto.AnalyticsResponse;
import com.autoassess.project.analytics.entity.Analytics;
import com.autoassess.project.analytics.repository.AnalyticsRepository;
import com.autoassess.project.security.AuthUtil;
import com.autoassess.project.user.entity.User;
import com.autoassess.project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private AnalyticsRepository analyticsRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/analytics")
    public AnalyticsResponse getAnalytics(){
        String email=authUtil.getCurrentUserEmail();

        User user=userRepository.findByEmail(email).orElseThrow();

        Analytics analytics=analyticsRepository.findByUserId(user.getId()).orElseThrow(()-> new RuntimeException("Analytics not found"));

        AnalyticsResponse response=new AnalyticsResponse();
        response.setTotalAttempts(analytics.getTotalAttempts());
        response.setAvgScore(analytics.getAvgScore());
        response.setBestScore(analytics.getBestScore());
        response.setWorstScore(analytics.getWorstScore());

        return response;

    }
}
