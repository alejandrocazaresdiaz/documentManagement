package com.clara.ops.document_management.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadFileRequest {
    private String user;
    private String name;
    private List<String> tags;
    private MultipartFile file;

}
