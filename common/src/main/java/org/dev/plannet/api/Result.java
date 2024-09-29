package org.dev.plannet.api;

import org.dev.plannet.error.ErrorCode;
import org.dev.plannet.error.ErrorCodeInterface;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
	private Integer resultCode;
	private String resultMessage;
	private String resultDescription;

	public static Result OK() {
		return Result.builder()
			.resultCode(ErrorCode.OK.getErrorCode())
			.resultMessage(ErrorCode.OK.getDescription())
			.resultDescription("성공")
			.build();
	}

	public static Result ERROR(ErrorCodeInterface errorCodeInterface) {
		return Result.builder()
			.resultCode(errorCodeInterface.getErrorCode())
			.resultMessage(errorCodeInterface.getDescription())
			.resultDescription(errorCodeInterface.getDescription())
			.build();
	}

	public static Result ERROR(ErrorCodeInterface errorCodeInterface, String message) {
		return Result.builder()
			.resultCode(errorCodeInterface.getErrorCode())
			.resultMessage(errorCodeInterface.getDescription())
			.resultDescription(message)
			.build();
	}
}
