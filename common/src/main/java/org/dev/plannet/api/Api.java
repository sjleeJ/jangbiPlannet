package org.dev.plannet.api;

import org.dev.plannet.error.ErrorCodeInterface;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

	private Result result;

	@Valid
	private T body;

	public static <T> Api<T> Ok(T data) {
		Api api = new Api();
		api.body = data;
		return api;
	}

	public static Api<Object> Error(ErrorCodeInterface errorCodeInterface) {
		Api api = new Api<Object>();
		api.result = Result.ERROR(errorCodeInterface);
		return api;
	}

	public static Api<Object> Error(ErrorCodeInterface errorCodeInterface, String description) {
		Api api = new Api<Object>();
		api.result = Result.ERROR(errorCodeInterface, description);
		return api;
	}
}
