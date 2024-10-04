package org.dev.plannet.error.code;

public enum TokenErrorCode implements ErrorCode {
	INVALID_SIGNATURE(401, 100, "잘못된서명"),
	UNSUPPORTED_JWT(401, 101, "지원되지 않는 서명"),
	ILLEGAL_TOKEN(401, 102, "잘못된 토큰"),
	UNAUTH_TOKEN(401, 103, "접근 불가 토큰"),
	AUTHORIZATION_TOKEN_NOT_FOUND(401, 104, "인증 헤더 토큰 없음"),
	EXPIRED_TOKEN(401, 105, "만료된 토큰"),
	
	;

	private final int statusCode;
	private final int code;
	private final String message;

	TokenErrorCode(int statusCode, int code, String message) {
		this.statusCode = statusCode;
		this.code = code;
		this.message = message;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Integer getStatusCode() {
		return statusCode;
	}
}
