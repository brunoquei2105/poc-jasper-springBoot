package com.example.jasper_spring.service.document;

import com.example.jasper_spring.models.document.Document;
import com.example.jasper_spring.models.document.DocumentFileStorage;
import com.example.jasper_spring.repository.document.DocumentRepository;
import com.example.jasper_spring.repository.document.FileStorageRepository;
import com.example.jasper_spring.utils.DocumentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService{

    private final DocumentRepository documentRepository;
    private final FileStorageRepository fileStorageRepository;

    private static final String FOLDER_STORAGE = "/home/brunoqueiroz/Documents/my_files_uploaded/";
    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, FileStorageRepository fileStorageRepository){
        this.documentRepository = documentRepository;
        this.fileStorageRepository = fileStorageRepository;
    }
// Upload and Download with Spring JPA
    @Override
    public Document uploadDoc(MultipartFile file) throws IOException {
        Document doc = Document.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .data(DocumentUtil.compressImage(file.getBytes()))
                .build();
        return documentRepository.save(doc);
    }

    @Override
    public byte[] dowloadDoc(String fileName) {
        Optional<Document> doc = documentRepository.findByFileName(fileName);
        if(doc.isPresent()){
            return DocumentUtil.decompressImage(doc.get().getData());
        }
        throw new NoSuchElementException("File not found");

    }
    // Upload and Download in File System
    @Override
    public String uploadToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_STORAGE + file.getOriginalFilename();
        DocumentFileStorage doc = fileStorageRepository.save(DocumentFileStorage.builder()
                        .fileName(file.getOriginalFilename())
                        .fileType(file.getContentType())
                        .filePath(filePath)
                        .build());
        file.transferTo(new File(filePath));
        if (doc != null){
            return "File upload successfully: " + file.getOriginalFilename();
        }
        return "File wasn't uploaded.";
        
    }
    @Override
    public byte[] downloadFromFileSystem(String fileName) throws IOException {
        Optional<DocumentFileStorage> doc = fileStorageRepository.findByFileName(fileName);
        if (doc.isPresent()){
            String filePath = doc.get().getFilePath();
            byte[] file = Files.readAllBytes(new File(filePath).toPath());
            return file;
        }
       throw new FileNotFoundException("File not found: " + fileName);
    }
}
