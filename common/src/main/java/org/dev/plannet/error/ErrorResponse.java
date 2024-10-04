package org.dev.plannet.error;

public class ErrorResponse {

	private int errorCode;
	private String message;
	private String details;  // 필요에 따라 추가 정보도 포함 가능

	public ErrorResponse(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public ErrorResponse(int errorCode, String message, String details) {
		this.errorCode = errorCode;
		this.message = message;
		this.details = details;
	}

	// Getters and Setters
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}