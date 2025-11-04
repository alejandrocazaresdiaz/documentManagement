package com.clara.ops.document_management.component;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.clara.ops.document_management.dto.UploadFileRequest;
import com.clara.ops.document_management.entity.DocumentEntity;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class DtoBuilder {

    public DocumentEntity fromUploadRequest(UploadFileRequest request) {
        DocumentEntity entity = new DocumentEntity();
        entity.setName(request.getName());
        entity.setUserOwner(request.getUser());        
        entity.setTags(request.getTags());
        entity.setType(request.getFile().getContentType());
        entity.setSize(request.getFile().getSize());
        entity.setCreatedAt(new Date());

        return entity;
    }
    
    
    public DocumentEntity fromUploadRequest(HttpServletRequest request, String contentType, long totalBytes) {
    	DocumentEntity entity = new DocumentEntity();
    	entity.setName(request.getParameter("name"));
        entity.setUserOwner(request.getParameter("user"));
        entity.setTags(Arrays.asList(request.getParameterValues("tags")));
        entity.setType(contentType);
        entity.setSize(totalBytes);
        entity.setCreatedAt(new Date());
    	return entity;
    }

    
}
