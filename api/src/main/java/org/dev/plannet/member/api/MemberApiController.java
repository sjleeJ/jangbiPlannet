package org.dev.plannet.member.api;

import org.dev.plannet.api.Api;
import org.dev.plannet.member.dto.check.MemberCheckResponse;
import org.dev.plannet.member.dto.signup.MemberSignUpDto;
import org.dev.plannet.member.dto.signup.MemberSignUpResponseDto;
import org.dev.plannet.member.presentation.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/open-api/v1/members")
@AllArgsConstructor
class MemberApiController {

	private final MemberService memberService;

	@PostMapping
	public Api<MemberSignUpResponseDto> signUp(@RequestBody @Valid MemberSignUpDto signUpDto) {
		MemberSignUpResponseDto response = memberService.saveMember(signUpDto);
		return Api.Ok(response);
	}

	@GetMapping("/email/{email}")
	public Api<MemberCheckResponse> emailDuplicated(@PathVariable String email) {
		MemberCheckResponse response = memberService.emailDuplicateCheck(email);
		return Api.Ok(response);
	}

}
