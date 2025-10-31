package com.clara.ops.document_management.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadFileExc extends RuntimeException {
//	
	public UploadFileExc(String message, Throwable cause) {//, MultipartFile file) {
        super(message, cause);
//        this.file = file;
    }
//
//	private MultipartFile file;
}
