package com.example.jasper_spring.service.document;

import com.example.jasper_spring.models.document.Document;
import com.example.jasper_spring.repository.document.DocumentRepository;
import com.example.jasper_spring.utils.DocumentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService{

    private final DocumentRepository documentRepository;
    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
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
}
