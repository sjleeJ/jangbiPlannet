package org.dev.plannet.member.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.dev.plannet.exception.ApiException;
import org.dev.plannet.member.Member;
import org.dev.plannet.member.MemberRepository;
import org.dev.plannet.member.dto.signup.MemberSignUpDto;
import org.dev.plannet.member.presentation.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

	@Mock
	MemberRepository memberRepository;

	@InjectMocks
	MemberService memberService;

	private Member member;

	@Test
	@DisplayName("회원가입 실패 - 이메일 중복")
	public void emailDuplicate() {
		// existsByEmail 메서드가 호출될 때 true 반환하도록 Stub 설정
		when(memberRepository.existsByEmail("test1@gmail.com")).thenReturn(true);

		// 테스트용 회원가입 DTO 생성
		MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test1@gmail.com", "12341234", "테스트", "01023452345");

		// 서비스 로직 호출 및 결과 검증: 이메일 중복으로 ApiException 발생 여부 확인
		assertThrows(ApiException.class, () -> memberService.saveMember(memberSignUpDto));

		// existsByEmail 메서드가 최소 한 번 호출되었는지 검증
		verify(memberRepository, atLeastOnce()).existsByEmail("test1@gmail.com");
	}
}
