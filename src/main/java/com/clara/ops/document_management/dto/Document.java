package com.clara.ops.document_management.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class Document {
	
	String id;
	String user;
	String name;
	List<String> tags;
	long size;
	String type;
	Date createdAt;

}
