package com.clara.ops.document_management.dto;

import java.util.List;

import lombok.Data;

@Data
public class DocumentRequest {
	
	String user;
	String name;
	List<String> tags;
	
}
