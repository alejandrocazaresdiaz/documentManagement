package com.clara.ops.document_management.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;


@Service
@Slf4j
@Profile("dev")
@RequiredArgsConstructor
public class MinioBucketServiceImp implements BucketService{

	@Value("${config.bucket.name}")
    private String bucketName;
	
	@Value("${config.cache.link.expiry}")
    private int expiry;
	
	@Value("${label.uploadSizeExc}")
    private String uploadSizeExcMsg;

	@Value("${label.downloadSizeExc}")
    private String downloadSizeExcMsg;	

    private final MinioClient minioClient;
    
    
    
    public void store(String name, MultipartFile file) throws UploadFileExc {
    	try {
    		log.info("bucket.store: {}",bucketName);
    		ObjectWriteResponse rsp = minioClient.putObject(
	                PutObjectArgs.builder()
	                        .bucket(bucketName)
	                        .object(name)
	                        .stream(file.getInputStream(), file.getSize(), -1)
	                        .contentType(file.getContentType())
	                        .build()
	        );
    		log.info(rsp.bucket().concat("/").concat(name));
        }catch(Exception e) {
        	log.error(e.getMessage());
        	throw new UploadFileExc(uploadSizeExcMsg + file.getOriginalFilename(), e.getCause());//, file);        	
        }
    }
    
    
    public void store(DocumentFile tempDocument)throws  UploadFileExc{
    	try (InputStream fileStream = Files.newInputStream(tempDocument.getPathFile())) {
    		log.info("bucket.store: {}",bucketName);
			
				minioClient.putObject(PutObjectArgs.builder()
						.bucket(bucketName)
						.object("uploads/" + tempDocument.getUniqueId() + "/" + tempDocument.getPathFile().getFileName())
						.stream(fileStream, tempDocument.getSize(), -1)
						.contentType(tempDocument.getContentType())
						.build());
			
    	}catch(Exception e) {
        	log.error(e.getMessage());
        	throw new UploadFileExc(uploadSizeExcMsg , e.getCause());//, file);        	
        }    	
    }
    
    public void store(Path tempFile, String uniqueId, String contentType) throws UploadFileExc {
    	try (InputStream fileStream = Files.newInputStream(tempFile)) {
    		log.info("bucket.store: {}",bucketName);
			
				minioClient.putObject(PutObjectArgs.builder().bucket(bucketName)
						.object("uploads/" + uniqueId + "/" + tempFile.getFileName())
						.stream(fileStream, Files.size(tempFile), -1)
						.contentType(contentType)
						.build());
			
    	}catch(Exception e) {
        	log.error(e.getMessage());
        	throw new UploadFileExc(uploadSizeExcMsg , e.getCause());//, file);        	
        }
    }
    
    
    
    public String generateDownloadUrl(String fileId) throws DownloadFileExc {
    	try {//InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, XmlParserException, ServerException, IllegalArgumentException, IOException {
    		return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(fileId)
                        .expiry(expiry, TimeUnit.MINUTES)
                        .build()
    				);
    	}catch(Exception e) {
    		log.error(e.getMessage());
    		throw new DownloadFileExc(downloadSizeExcMsg + fileId, e.getCause());
    	}
    }

}
