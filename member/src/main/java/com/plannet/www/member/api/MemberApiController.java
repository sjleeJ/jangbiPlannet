package com.plannet.www.member.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plannet.www.member.dto.check.MemberCheckResponse;
import com.plannet.www.member.dto.signin.MemberSignInDto;
import com.plannet.www.member.dto.signin.MemberSignInResponseDto;
import com.plannet.www.member.dto.signup.MemberSignUpDto;
import com.plannet.www.member.dto.signup.MemberSignUpResponseDto;
import com.plannet.www.member.presentation.MemberService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/open-api/v1/members")
@AllArgsConstructor
class MemberApiController {

	private final MemberService memberService;

	@PostMapping
	public ResponseEntity<MemberSignUpResponseDto> signUp(@RequestBody @Valid MemberSignUpDto signUpDto) {
		MemberSignUpResponseDto response = memberService.saveMember(signUpDto);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<MemberSignInResponseDto> signIn(@RequestBody @Valid MemberSignInDto signInDto) {
		MemberSignInResponseDto response = memberService.signIn(signInDto);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<MemberCheckResponse> emailDuplicated(@PathVariable String email) {
		MemberCheckResponse response = memberService.emailDuplicateCheck(email);
		return ResponseEntity.ok(response);
	}

}
