package com.example.jasper_spring.controller.document;

import com.example.jasper_spring.models.document.Document;
import com.example.jasper_spring.service.document.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1.0/document")
@Tag(
        name = "Document-API"
)
public class DocumentController {

    private final DocumentService documentService;
    @Autowired
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }
    @Operation(
            responses = {@ApiResponse(description = "Save a file on Document DB table", responseCode = "201")},
            summary = "",
            tags = {}
    )
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Document> uploadDoc(@RequestParam MultipartFile file) throws IOException {

        Document doc;
        try{

            doc =  documentService.uploadDoc(file);

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot upload file", e.getCause());
        }
        return new ResponseEntity<>(doc, HttpStatus.CREATED);

    }
    @Operation(
            responses = {@ApiResponse(description = "Retrieve a file from DB.", responseCode = "201")},
            summary = "",
            tags = {}
    )
    @GetMapping(value = "/download/{fileName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> downloadDoc(@PathVariable String fileName){

        byte[] doc;
        try{
            doc = documentService.dowloadDoc(fileName);

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot upload file", e.getCause());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ fileName)
                .body(doc);

    }
}
