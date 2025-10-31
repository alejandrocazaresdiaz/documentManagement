package com.clara.ops.document_management.exception;

import lombok.Builder;

@Builder
public record HttpErrorRsp(String location, String details) {
	
	

}
