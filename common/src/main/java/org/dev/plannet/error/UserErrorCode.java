package org.dev.plannet.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeInterface {

	USER_NOT_FOUND(400, 100, "사용자를 찾을 수 없음"),
	EXIST_USER_EMAIL(400, 101, "사용자 이메일이 이미 있음"),
	EXIST_USER_NICKNAME(400, 102, "사용자 닉네임이 이미 있음"),
	;

	private final Integer httpStatusCode;
	private final Integer errorCode;
	private final String description;
}
