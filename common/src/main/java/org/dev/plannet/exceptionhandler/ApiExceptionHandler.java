package org.dev.plannet.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.dev.plannet.api.Api;
import org.dev.plannet.error.ErrorCodeInterface;
import org.dev.plannet.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE) // 최우선처리
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiException(
            ApiException apiException
    ){
        log.error("",apiException);

        ErrorCodeInterface errorCodeInterface = apiException.getErrorCodeInterface();
        return ResponseEntity
                .status(errorCodeInterface.getHttpStatusCode())
                .body(
                        Api.Error(errorCodeInterface, apiException.getErrorDescription())
                );
    }
}
