package com.stripe.system.exception;

import org.springframework.http.HttpStatus;

public class ProcessingException extends RuntimeException {
	private final String errorCode;
	private final String errorMessage;
	private final HttpStatus httpStatus;
	
	public ProcessingException(String errorCode, String errorMessage,HttpStatus httpStatus) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String toString() {
		return "ProcessingException [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", httpStatus="
				+ httpStatus + "]";
	}

	
	

}
