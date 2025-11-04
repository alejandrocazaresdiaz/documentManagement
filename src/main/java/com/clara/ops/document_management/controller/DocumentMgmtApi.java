package com.clara.ops.document_management.controller;

import java.nio.file.Files;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clara.ops.document_management.component.DtoBuilder;
import com.clara.ops.document_management.component.TempFileBuilder;
import com.clara.ops.document_management.dto.DocumentFile;
import com.clara.ops.document_management.dto.DocumentRequest;
import com.clara.ops.document_management.dto.SearchResponse;
import com.clara.ops.document_management.dto.UploadFileRequest;
import com.clara.ops.document_management.entity.DocumentEntity;
import com.clara.ops.document_management.service.BucketService;
import com.clara.ops.document_management.service.DocumentService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${config.api.documentMgmt.basePath}")
@RequiredArgsConstructor
public class DocumentMgmtApi {

	private final BucketService bucketService;

	private final DocumentService docPersistenceService;

	private final DtoBuilder builder;
	
	private final TempFileBuilder fileStreamBuilder;
	
    @PostMapping(value="${config.api.documentMgmt.clasicUpload}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> clasicUploadFile(@ModelAttribute UploadFileRequest request) throws Exception {
    	bucketService.store(request.getName(), request.getFile());
    	docPersistenceService.save(builder.fromUploadRequest(request));
    	
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    
    
    @PostMapping(value="${config.api.documentMgmt.upload}")
    public ResponseEntity<Void> uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
    	DocumentFile tempDocument = fileStreamBuilder.build(file);
        bucketService.store(tempDocument);
        DocumentEntity document = builder.fromUploadRequest(request, tempDocument.getContentType(), tempDocument.getSize());
        docPersistenceService.save(document);        
        
        Files.deleteIfExists(tempDocument.getPathFile());
        Files.deleteIfExists(tempDocument.getPathDir());
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    
    @PostMapping("${config.api.documentMgmt.search}")
    public SearchResponse searchFiles(
    		@RequestParam Long page,
            @RequestParam Long size,
            @RequestParam List<String> sort,
    		@RequestBody DocumentRequest docRef) {
    	log.warn("Por implementar");
    	
    	return docPersistenceService.listByCriteria(page, size, sort, docRef);
    }

//    @GetMapping("/download/{documentId}")
    @GetMapping("${config.api.documentMgmt.download}")
    public ResponseEntity<String> downloadFile(@PathVariable String documentId) {
    	log.warn("Por implementar un proxy y ocultar path de bucket");
        try {
            String url = bucketService.generateDownloadUrl(documentId);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Archivo no encontrado: " + documentId);
        }        
    }
    
    
    
    @GetMapping("${config.api.documentMgmt.listAll}")
    public List<DocumentEntity> listAll(){
    	return docPersistenceService.listAll();
    }
    		

	
}
