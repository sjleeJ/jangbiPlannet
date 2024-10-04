package org.dev.plannet.member.dto.signin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberSignInDto(
	@NotBlank @Email String email,
	@NotBlank String password
) {
	public MemberSignInDto of(String email, String password) {
		return new MemberSignInDto(email, password);
	}

}
