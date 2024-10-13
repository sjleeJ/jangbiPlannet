package com.plannet.www.member.dto.signup;

public record MemberSignUpResponseDto(
	String email,
	String nickname,
	String phone
) {
	public static MemberSignUpResponseDto of(String email, String nickname, String phone) {
		return new MemberSignUpResponseDto(email, nickname, phone);
	}
}
