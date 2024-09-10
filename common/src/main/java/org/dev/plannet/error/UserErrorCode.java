package org.dev.plannet.error;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeInterface{

    USER_NOT_FOUND(400,100,"사용자를 찾을 수 없음"),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
