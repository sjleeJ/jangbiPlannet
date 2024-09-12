package org.dev.plannet.member.service;

import org.dev.plannet.exception.ApiException;
import org.dev.plannet.member.Member;
import org.dev.plannet.member.MemberRepository;
import org.dev.plannet.member.dto.signup.MemberSignUpDto;
import org.dev.plannet.member.presentation.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    MemberRepository memberRepository;
    @InjectMocks
    MemberService memberService;

    @BeforeEach
    public void setUp() {
        Member member = Member.builder()
                .email("test@gmail.com")
                .password("12341234")
                .nickname("테슨트")
                .phone("01023444455")
                .build();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("회원가입 실패-닉네임 중복")
    public void nicknameDuplicated(){
        when(memberRepository.existsByEmail("테스트")).thenReturn(true);

        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test@gmail.com","12341234","테스트","01023452345");
        // then: 서비스 로직 호출 및 결과 검증
        assertThrows(ApiException.class, () -> memberService.saveMember(memberSignUpDto));
        verify(memberRepository, atLeastOnce()).existsByNickname("테스트");
    }

    @Test
    @DisplayName("회원가입 실패 - 이메일 중복")
    public void emailDuplicate() {
        when(memberRepository.existsByEmail("test@gmail.com")).thenReturn(true);

        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test@gmail.com","12341234","테스트","01023452345");
        // then: 서비스 로직 호출 및 결과 검증
        assertThrows(ApiException.class, () -> memberService.saveMember(memberSignUpDto));

        verify(memberRepository, atLeastOnce()).existsByEmail("test@gmail.com");
    }

}
