package org.dev.plannet.error;

import org.dev.plannet.error.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorStatusCode()));
	}
}
