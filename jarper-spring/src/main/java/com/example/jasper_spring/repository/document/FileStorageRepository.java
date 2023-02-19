package com.example.jasper_spring.repository.document;

import com.example.jasper_spring.models.document.DocumentFileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileStorageRepository extends JpaRepository<DocumentFileStorage, Long> {

    Optional<DocumentFileStorage> findByFileName(String fileName);
}
