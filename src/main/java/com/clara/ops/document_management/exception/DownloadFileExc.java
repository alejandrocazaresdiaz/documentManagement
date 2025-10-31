package com.clara.ops.document_management.exception;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class DownloadFileExc extends RuntimeException {
	
	public DownloadFileExc(String message, Throwable cause) {
        super(message, cause);
    }
}