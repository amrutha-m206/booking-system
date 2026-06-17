package com.autoassess.project.autoassessAI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autoassess.project.autoassessAI.entity.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Long> {

}
