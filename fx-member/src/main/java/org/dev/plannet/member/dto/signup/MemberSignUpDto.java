package org.dev.plannet.member.dto.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberSignUpDto(
	@NotBlank @Email String email,
	@NotBlank String password,
	String nickname,
	@NotBlank String phone
) {
	public MemberSignUpDto of(String email, String password, String nickname, String phone) {
		return new MemberSignUpDto(email, password, nickname, phone);
	}

}
