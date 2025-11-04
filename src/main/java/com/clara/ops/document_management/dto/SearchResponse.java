package com.clara.ops.document_management.dto;

import java.util.List;

import com.clara.ops.document_management.entity.DocumentEntity;

import lombok.Data;

@Data
public class SearchResponse {
	
	private SearchMetadata metadata;
	
	private List<DocumentEntity> documents;
}
