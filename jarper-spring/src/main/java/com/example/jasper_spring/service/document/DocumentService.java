package com.example.jasper_spring.service.document;

import com.example.jasper_spring.models.document.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DocumentService {

    Document uploadDoc(MultipartFile file) throws IOException;
    byte[] dowloadDoc(String fileName);
    String uploadToFileSystem(MultipartFile file) throws IOException;
    byte[] downloadFromFileSystem(String fileName) throws IOException;
}
