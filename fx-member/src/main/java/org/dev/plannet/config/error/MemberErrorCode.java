package org.dev.plannet.config.error;

import org.dev.plannet.error.code.ErrorCode;

public enum MemberErrorCode implements ErrorCode {
	NOT_FOUND_ROLE(400, 200, "유저 규칙을 못찾음"),
	NOT_EXIST_USER(400, 201, "유저가 존재하지 않음"),
	EXIST_USER_EMAIL(400, 202, "유저 이메일이 이미 존재함"),
	EXIST_USER_NICKNAME(400, 203, "유저 닉네임 이미 존재함"),
	;

	private final int statusCode;
	private final int code;
	private final String message;

	MemberErrorCode(int statusCode, int code, String message) {
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
