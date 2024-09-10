package org.dev.plannet.exception;

import lombok.Getter;
import org.dev.plannet.error.ErrorCodeInterface;

@Getter
public class ApiException  extends RuntimeException implements ApiExceptionInterface{

    private final ErrorCodeInterface errorCodeInterface;
    private final String errorDescription;

    public ApiException(ErrorCodeInterface errorCodeInterface){
        super(errorCodeInterface.getDescription());
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = errorCodeInterface.getDescription();
    }

    public ApiException(ErrorCodeInterface errorCodeInterface,String description){
        super(description);
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = description;
    }

}
