package com.clara.ops.document_management.dto;

import java.nio.file.Path;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentFile {

	private String uniqueId;
	private Path pathFile;
	private Path pathDir;
	private long size;
	private String contentType;
	
	public DocumentFile() {
		this.uniqueId = UUID.randomUUID().toString();
	}
}
