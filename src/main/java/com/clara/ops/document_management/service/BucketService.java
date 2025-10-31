package com.clara.ops.document_management.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clara.ops.document_management.exception.DownloadFileExc;
import com.clara.ops.document_management.exception.UploadFileExc;

@Service
public interface BucketService {

	public void store(String name, MultipartFile file) throws  UploadFileExc;
	
	public String generateDownloadUrl(String fileId) throws DownloadFileExc;
	
}
