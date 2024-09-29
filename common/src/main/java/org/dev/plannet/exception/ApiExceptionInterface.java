package org.dev.plannet.exception;

import org.dev.plannet.error.ErrorCodeInterface;

public interface ApiExceptionInterface {
	ErrorCodeInterface getErrorCodeInterface();

	String getErrorDescription();
}
