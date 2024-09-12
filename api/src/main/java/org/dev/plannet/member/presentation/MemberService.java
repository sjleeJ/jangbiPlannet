package org.dev.plannet.member.presentation;

import lombok.AllArgsConstructor;
import org.dev.plannet.encrypt.EncryptionUtils;
import org.dev.plannet.error.UserErrorCode;
import org.dev.plannet.exception.ApiException;
import org.dev.plannet.member.Member;
import org.dev.plannet.member.MemberRepository;
import org.dev.plannet.member.dto.check.MemberCheckResponse;
import org.dev.plannet.member.dto.signup.MemberSignUpDto;
import org.dev.plannet.member.dto.signup.MemberSignUpResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final EncryptionUtils encryptionUtils;

    public MemberSignUpResponseDto saveMember(MemberSignUpDto signUpDto) {
        if(memberRepository.existsByEmail(signUpDto.email())) throw new ApiException(UserErrorCode.EXIST_USER_EMAIL);
        if(memberRepository.existsByNickname(signUpDto.nickname())) throw new ApiException(UserErrorCode.EXIST_USER_NICKNAME);
        Member member = memberRepository.save(
                Member.builder()
                .email(signUpDto.email())
                .password(encryptionUtils.encrypt(signUpDto.password()))
                .phone(signUpDto.phone())
                .nickname(signUpDto.nickname())
                .build()
        );
        return MemberSignUpResponseDto.of(member.getEmail(),member.getNickname(),member.getPhone());
    }

    public MemberCheckResponse emailDuplicateCheck(String email) {
        Member member = memberRepository.findByEmail(email);
        if(member != null) return MemberCheckResponse.of(false);
        return MemberCheckResponse.of(true);
    }

}
