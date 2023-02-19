package com.example.jasper_spring.repository.document;

import com.example.jasper_spring.models.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findByFileName(String fileName);
}
