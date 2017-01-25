package com.avenuecode.core.exception;

/**
 * This class represents an error message response.
 *
 * @author Luis Eduardo Oliveira Lizardo
 *
 */
public class ErrorResponse {
	
	private int errorCode;
	private String message;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}