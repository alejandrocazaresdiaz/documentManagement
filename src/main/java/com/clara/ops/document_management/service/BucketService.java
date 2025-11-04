package com.clara.ops.document_management.service;

import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clara.ops.document_management.dto.DocumentFile;
import com.clara.ops.document_management.exception.DownloadFileExc;
import com.clara.ops.document_management.exception.UploadFileExc;

@Service
public interface BucketService {

	public void store(String name, MultipartFile file) throws  UploadFileExc;
	
	public void store(Path tempFile, String uniqueId, String contentType)throws  UploadFileExc;
	
	public void store(DocumentFile tempDocument)throws  UploadFileExc;
	
	public String generateDownloadUrl(String fileId) throws DownloadFileExc;
	
}
