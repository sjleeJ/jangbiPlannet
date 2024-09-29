package org.dev.plannet.member.dto.signin;

import jakarta.validation.constraints.NotBlank;

public record MemberSignInResponseDto(
	@NotBlank String username
) {
	public MemberSignInResponseDto of(String username) {
		return new MemberSignInResponseDto(username);
	}
}
