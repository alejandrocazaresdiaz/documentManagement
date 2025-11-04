package com.clara.ops.document_management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.clara.ops.document_management.Util;
import com.clara.ops.document_management.controller.DocumentMgmtApi;
import com.clara.ops.document_management.dto.DocumentRequest;
import com.clara.ops.document_management.dto.SearchMetadata;
import com.clara.ops.document_management.dto.SearchResponse;
import com.clara.ops.document_management.entity.DocumentEntity;
import com.clara.ops.document_management.repository.DocumentRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {
	
    private final DocumentRepository repository;

    public DocumentEntity save(DocumentEntity doc) {
        return repository.save(doc);
    }

    public SearchResponse listByCriteria( Long page, Long size, List<String> sort, DocumentRequest docRef) {
    	SearchMetadata metadata = new SearchMetadata();		
		metadata.setCurrentPage(page);
		metadata.setItemsPerPage(size);
		
		Long totalItems=  repository.countDocumentsByFilters(docRef.getUser(), docRef.getName(), docRef.getTags());		
		metadata.setTotalItems(totalItems);
		metadata.setTotalPages(Util.calculateTotalPages(totalItems.intValue(), size.intValue()));
		
		SearchResponse response = new SearchResponse();
    	response.setMetadata(metadata);
    	
    	log.info("{}",docRef);
    	Pageable pageable = PageRequest.of(page.intValue(), size.intValue(), Sort.by("createdAt").ascending());
    	List<DocumentEntity> result = repository.searchDocuments(docRef.getUser(), docRef.getName(), docRef.getTags(), pageable);
    	response.setDocuments(result);
    	metadata.setCurrentItems(result.size());
		return response;
    	
    }
    
    public List<DocumentEntity> listAll(){
      return repository.findAll();
  }

    public DocumentEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }


}
