package com.plannet.www.member.presentation;

import com.plannet.www.member.dto.check.MemberCheckResponse;
import com.plannet.www.member.dto.signin.MemberSignInDto;
import com.plannet.www.member.dto.signin.MemberSignInResponseDto;
import com.plannet.www.member.dto.signup.MemberSignUpDto;
import com.plannet.www.member.dto.signup.MemberSignUpResponseDto;

public interface MemberService {
	MemberSignUpResponseDto saveMember(MemberSignUpDto signUpDto);

	MemberCheckResponse emailDuplicateCheck(String email);

	MemberSignInResponseDto signIn(MemberSignInDto request);
}
