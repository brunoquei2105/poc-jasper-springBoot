package com.example.jasper_spring.models.document;

import jakarta.mail.Multipart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DOCUMENTS")
@Builder
public class Document implements Serializable {
    private static final long serialVersionUID = 2770802108847287261L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    @Column(name = "data", length = 100000)
    private byte[] data;


}
