package com.autoassess.project.analytics.repository;

import com.autoassess.project.analytics.entity.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnalyticsRepository extends JpaRepository<Analytics,Long> {
   Optional<Analytics> findByUserId(Long userId);

}
