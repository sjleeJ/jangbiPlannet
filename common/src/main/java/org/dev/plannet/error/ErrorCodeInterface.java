package org.dev.plannet.error;

public interface ErrorCodeInterface {

    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
