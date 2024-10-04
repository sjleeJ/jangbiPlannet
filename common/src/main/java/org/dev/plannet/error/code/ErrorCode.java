package org.dev.plannet.error.code;

public interface ErrorCode {
	Integer getCode();     // 에러 코드 반환

	Integer getStatusCode();

	String getMessage();  // 에러 메시지 반환
}
