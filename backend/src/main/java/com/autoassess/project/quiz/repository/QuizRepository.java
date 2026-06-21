package com.autoassess.project.quiz.repository;

import com.autoassess.project.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

    Optional<Quiz> findByDocumentId(Long documentId);
}
