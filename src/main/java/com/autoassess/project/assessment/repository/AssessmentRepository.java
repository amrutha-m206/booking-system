package com.autoassess.project.assessment.repository;

import com.autoassess.project.assessment.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment,Long> {
}
