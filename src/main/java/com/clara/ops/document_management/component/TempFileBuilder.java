package com.clara.ops.document_management.component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.clara.ops.document_management.dto.DocumentFile;
import com.clara.ops.document_management.exception.FileBuilderExc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TempFileBuilder {

	public DocumentFile build(MultipartFile file) throws Exception {
		DocumentFile tempFile = new DocumentFile();
		tempFile.setContentType(file.getContentType());
		try {
	    	tempFile.setPathDir(Files.createTempDirectory("upload_" + tempFile.getUniqueId()));    	
	        tempFile.setPathFile(tempFile.getPathDir().resolve(file.getOriginalFilename() != null ? file.getOriginalFilename() : tempFile.getUniqueId()+"_data.bin"));
	        log.info("Creando carpeta temporal: {}", tempFile.getPathDir());
    	
//    	try (
			InputStream inputStream = file.getInputStream();
			FileOutputStream outputStream = new FileOutputStream(tempFile.getPathFile().toFile());
//		) {
    		byte[] buffer = new byte[1024 * 1024]; // 1MB buffer
            int bytesRead;            

            while ((bytesRead = inputStream.read(buffer)) != -1) {
            	log.info("Procesado por partes...");
            	outputStream.write(buffer, 0, bytesRead);//processChunk
            	tempFile.setSize(tempFile.getSize()+bytesRead);
            }
            log.info("Proceso completo: {} bytes", tempFile.getSize());
            outputStream.close();//!
            inputStream.close();
    	}catch (IOException e) {
    		throw new FileBuilderExc("Error al construir el archivo", e);
    	}        
        return tempFile;        
	}
}
