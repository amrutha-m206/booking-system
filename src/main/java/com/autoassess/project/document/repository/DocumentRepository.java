package com.autoassess.project.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autoassess.project.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document,Long> {

}
