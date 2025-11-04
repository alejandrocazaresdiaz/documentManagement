package com.clara.ops.document_management.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clara.ops.document_management.dto.DocumentFile;
import com.clara.ops.document_management.dto.UploadFileRequest;
import com.clara.ops.document_management.exception.DownloadFileExc;
import com.clara.ops.document_management.exception.GlobalExceptionHandler;
import com.clara.ops.document_management.exception.UploadFileExc;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;


@Service
@Slf4j
@Profile("prod")
@RequiredArgsConstructor
public class S3BucketServiceImp implements BucketService{

	@Value("${config.bucket.name}")
    private String bucketName;
	
	@Value("${label.uploadSizeExc}")
    private String uploadSizeExcMsg;

	@Value("${label.downloadSizeExc}")
    private String downloadSizeExcMsg;	

    
    
    public void store(String name, MultipartFile file) throws UploadFileExc {
        	log.error("Not implemented yet");        
    }
    
    public String generateDownloadUrl(String fileId) throws DownloadFileExc {
    	log.error("Not implemented yet");
    	return "Not implemented yet";
    }

	public void store(Path tempFile, String uniqueId, String contentType) throws UploadFileExc {
		log.error("Not implemented yet");
	}
	
	public void store(DocumentFile tempDocument)throws  UploadFileExc{
		log.error("Not implemented yet");
	}

}
