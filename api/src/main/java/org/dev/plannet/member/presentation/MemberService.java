package org.dev.plannet.member.presentation;

import lombok.AllArgsConstructor;
import org.dev.plannet.encrypt.EncryptionUtils;
import org.dev.plannet.error.ErrorCode;
import org.dev.plannet.error.UserErrorCode;
import org.dev.plannet.exception.ApiException;
import org.dev.plannet.member.Member;
import org.dev.plannet.member.MemberRepository;
import org.dev.plannet.member.dto.check.MemberCheckResponse;
import org.dev.plannet.member.dto.signup.MemberSignUpDto;
import org.dev.plannet.member.dto.signup.MemberSignUpResponseDto;
import org.dev.plannet.member.role.MemberRole;
import org.dev.plannet.member.role.MemberRoleRepository;
import org.dev.plannet.role.Role;
import org.dev.plannet.role.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final EncryptionUtils encryptionUtils;
    private final MemberRoleRepository memberRoleRepository;
    private final String ROLE_USER = "ROLE_USER";

    @Transactional
    public MemberSignUpResponseDto saveMember(MemberSignUpDto signUpDto) {
        if(memberRepository.existsByEmail(signUpDto.email())) throw new ApiException(UserErrorCode.EXIST_USER_EMAIL);
        if(memberRepository.existsByNickname(signUpDto.nickname())) throw new ApiException(UserErrorCode.EXIST_USER_NICKNAME);

        LocalDateTime now = LocalDateTime.now();

        Member member = memberRepository.save(
                Member.builder()
                        .email(signUpDto.email())
                        .password(encryptionUtils.encrypt(signUpDto.password()))
                        .phone(signUpDto.phone())
                        .nickname(signUpDto.nickname())
                        .createdAt(now)  // 현재 시간으로 createdAt 설정
                        .build()
        );

        Role role = roleRepository.findByName(ROLE_USER).orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));

        memberRoleRepository.save(
                MemberRole.builder()
                        .member(member)
                        .role(role)
                        .createdAt(now)
                        .build()
        );
        return MemberSignUpResponseDto.of(member.getEmail(),member.getNickname(),member.getPhone());
    }

    public MemberCheckResponse emailDuplicateCheck(String email) {
        Boolean response = memberRepository.existsByEmail(email);

        return MemberCheckResponse.of(response);
    }

}
