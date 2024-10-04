package org.dev.plannet.error.exceptions;

import org.dev.plannet.error.code.ErrorCode;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final ErrorCode errorCode;  // 에러 코드 필드 추가

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public CustomException(ErrorCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode.getCode();  // 에러 코드 반환
	}

	public int getErrorStatusCode() {
		return errorCode.getStatusCode();
	}

	@Override
	public String getMessage() {
		return errorCode.getMessage();  // 에러 메시지 반환
	}
}
