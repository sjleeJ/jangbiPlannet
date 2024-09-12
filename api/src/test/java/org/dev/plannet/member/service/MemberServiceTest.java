package org.dev.plannet.member.service;

import org.dev.plannet.exception.ApiException;
import org.dev.plannet.member.Member;
import org.dev.plannet.member.MemberRepository;
import org.dev.plannet.member.dto.signup.MemberSignUpDto;
import org.dev.plannet.member.presentation.MemberService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    private Member member;


    @Test
    @DisplayName("회원가입 실패 - 닉네임 중복")
    public void nicknameDuplicated(){
        // existsByNickname 메서드가 호출될 때 true 반환하도록 Stub 설정
        when(memberRepository.existsByNickname("테스트")).thenReturn(true);

        // 테스트용 회원가입 DTO 생성
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test@gmail.com", "12341234", "테스트", "01023452345");

        // 서비스 로직 호출 및 결과 검증: 닉네임 중복으로 ApiException 발생 여부 확인
        assertThrows(ApiException.class, () -> memberService.saveMember(memberSignUpDto));

        // existsByNickname 메서드가 최소 한 번 호출되었는지 검증
        verify(memberRepository, atLeastOnce()).existsByNickname("테스트");
    }

    @Test
    @DisplayName("회원가입 실패 - 이메일 중복")
    public void emailDuplicate() {
        // existsByEmail 메서드가 호출될 때 true 반환하도록 Stub 설정
        when(memberRepository.existsByEmail("test@gmail.com")).thenReturn(true);

        // 테스트용 회원가입 DTO 생성
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test@gmail.com", "12341234", "테스트", "01023452345");

        // 서비스 로직 호출 및 결과 검증: 이메일 중복으로 ApiException 발생 여부 확인
        assertThrows(ApiException.class, () -> memberService.saveMember(memberSignUpDto));

        // existsByEmail 메서드가 최소 한 번 호출되었는지 검증
        verify(memberRepository, atLeastOnce()).existsByEmail("test@gmail.com");
    }
}
