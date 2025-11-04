package com.clara.ops.document_management.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

//import com.clara.ops.document_management.component.MessageBuilder;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
@lombok.RequiredArgsConstructor
public class GlobalExceptionHandler {

	@Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;
	
	@Value("${label.maxUploadSizeExc}")
    private String maxUploadSizeExcMsg;
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public HttpErrorRsp resolveExc(final HttpServletRequest req, final MaxUploadSizeExceededException ex) {
	    return HttpErrorRsp.builder()
	    		.location(req.getRequestURI())
	    		.details(maxUploadSizeExcMsg + maxFileSize)
	            .build();
	}
	
	@ExceptionHandler({UploadFileExc.class, DownloadFileExc.class, FileBuilderExc.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public HttpErrorRsp resolveMinioExc(final HttpServletRequest req, final Exception ex) {
		log.error("{}", ex.getClass());
		return HttpErrorRsp.builder()
	    		.location(req.getRequestURI())
	            .details(ex.getMessage())
	            .build();
	}
	
	
	@ExceptionHandler({Exception.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public HttpErrorRsp resolveGeneralExc(final HttpServletRequest req, final Exception ex) {
		return HttpErrorRsp.builder()
	    		.location(req.getRequestURI())
	            .details(ex.getMessage())
	            .build();
	}
	

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public HttpErrorRsp argExc(final HttpServletRequest req, final IllegalArgumentException ex) {
	    return HttpErrorRsp.builder()
	    		.location(req.getRequestURI())
	    		.details(ex.getMessage())
	            .build();
	}
	
}
