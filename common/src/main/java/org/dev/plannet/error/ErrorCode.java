package org.dev.plannet.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeInterface {

	OK(200, 200, "성공"),
	BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
	SERVER_ERROR(500, 500, "서버 에러"),
	NULL_POINT(500, 512, "Null point"),

	;

	private final Integer httpStatusCode;
	private final Integer errorCode;
	private final String description;

}
