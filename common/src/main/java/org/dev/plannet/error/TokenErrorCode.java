package org.dev.plannet.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenErrorCode implements ErrorCodeInterface{
    INVALID_TOKEN(400,2000,"유효하지 않은 토큰."),
    EXPIRED_TOKEN(400,2001,"만료된 토큰"),
    TOKEN_EXCEPTION(400,2002,"토큰 알 수 없는 에러"),
    INVALID_SIGNATURE(400,2003,"잘못된서명"),
    UNSUPPORTED_JWT(400,2004,"지원되지 않는 서명"),
    ILLEGAL_TOKEN(400,2005,"잘못된 토큰"),

    UNAUTH_TOKEN(400,203,"접근 불가 토큰"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400,2003,"인증 헤더 토큰 없음"),
    ;
    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

}