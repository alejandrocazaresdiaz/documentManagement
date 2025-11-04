package com.clara.ops.document_management.dto;

import lombok.Data;

@Data
public class SearchMetadata {
	
	private long currentPage;
	private long itemsPerPage;
	private long currentItems;
	private long totalPages;
	private long totalItems;

}
