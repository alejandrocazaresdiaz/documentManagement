package com.clara.ops.document_management.controller;

import java.util.Date;
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
import java.net.URI;

import com.clara.ops.document_management.dto.DocumentRequest;
import com.clara.ops.document_management.dto.UploadFileRequest;
import com.clara.ops.document_management.entity.DocumentEntity;
import com.clara.ops.document_management.service.BucketService;
import com.clara.ops.document_management.service.DocumentService;
import com.clara.ops.document_management.service.MinioBucketServiceImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${config.api.documentMgmt.basePath}")
@RequiredArgsConstructor
public class DocumentMgmtApi {

	private final BucketService bucketService;

	private final DocumentService service;

	
    @PostMapping(value="${config.api.documentMgmt.upload}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(@ModelAttribute UploadFileRequest request) throws Exception {
    	log.info("Tags ${}",request.getTags());
//    	return new ResponseEntity<>(HttpStatus.CREATED);
    	DocumentEntity entity = new DocumentEntity();
    	entity.setCreatedAt(new Date());
    	entity.setName("Alex1");
    	entity.setSize(47);
    	entity.setType("pdf");
    	entity.setUserOwner("yo");
    	ResponseEntity.ok(service.guardar(entity));

    	bucketService.store(request.getName(), request.getFile());
//    	return ResponseEntity.created(URI.create("/document-managemen/download/" + "newResourceId")).build();
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    
    

//    @PostMapping("/search")
    @PostMapping("${config.api.documentMgmt.search}")
    public List<String> searchFiles(
    		@RequestParam String page,
            @RequestParam String size,
            @RequestParam List<String> sort,
    		@RequestBody DocumentRequest metadata) {

        // Lógica para buscar archivos
        return  List.of("documento1.pdf", "documento2.docx");
//        return ResponseEntity.ok(resultados);
    }

//    @GetMapping("/download/{documentId}")
    @GetMapping("${config.api.documentMgmt.download}")
    public ResponseEntity<String> downloadFile(@PathVariable String documentId) {
        try {
            String url = bucketService.generateDownloadUrl(documentId);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Archivo no encontrado: " + documentId);
        }

        // Lógica para descargar el archivo
//        return ResponseEntity.ok("Descargando archivo con ID: " + documentId);
    }

	
}
