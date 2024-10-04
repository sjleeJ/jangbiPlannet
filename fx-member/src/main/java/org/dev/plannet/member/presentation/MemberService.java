package org.dev.plannet.member.presentation;

import java.time.LocalDateTime;

import org.dev.plannet.config.error.MemberErrorCode;
import org.dev.plannet.encrypt.EncryptionUtils;
import org.dev.plannet.error.exceptions.CustomException;
import org.dev.plannet.jwt.TokenProvider;
import org.dev.plannet.member.Member;
import org.dev.plannet.member.MemberRepository;
import org.dev.plannet.member.dto.check.MemberCheckResponse;
import org.dev.plannet.member.dto.signin.MemberSignInDto;
import org.dev.plannet.member.dto.signin.MemberSignInResponseDto;
import org.dev.plannet.member.dto.signup.MemberSignUpDto;
import org.dev.plannet.member.dto.signup.MemberSignUpResponseDto;
import org.dev.plannet.member.role.MemberRole;
import org.dev.plannet.member.role.MemberRoleRepository;
import org.dev.plannet.role.Role;
import org.dev.plannet.role.RoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	private final MemberRepository memberRepository;
	private final RoleRepository roleRepository;
	private final EncryptionUtils encryptionUtils;
	private final MemberRoleRepository memberRoleRepository;
	private final TokenProvider tokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final String ROLE_USER = "ROLE_USER";

	@Transactional
	public MemberSignUpResponseDto saveMember(MemberSignUpDto signUpDto) {
		if (memberRepository.existsByEmail(signUpDto.email()))
			throw new CustomException(MemberErrorCode.EXIST_USER_EMAIL);
		if (memberRepository.existsByNickname(signUpDto.nickname()))
			throw new CustomException(MemberErrorCode.EXIST_USER_NICKNAME);

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

		Role role = roleRepository.findByName(ROLE_USER)
			.orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_ROLE));

		memberRoleRepository.save(
			MemberRole.builder()
				.member(member)
				.role(role)
				.createdAt(now)
				.build()
		);
		return MemberSignUpResponseDto.of(member.getEmail(), member.getNickname(), member.getPhone());
	}

	public MemberCheckResponse emailDuplicateCheck(String email) {
		Boolean response = memberRepository.existsByEmail(email);

		return MemberCheckResponse.of(response);
	}

	public MemberSignInResponseDto signIn(MemberSignInDto request) {
		Member member = memberRepository.findByEmail(request.email()).orElseThrow(
			() -> new CustomException(MemberErrorCode.NOT_EXIST_USER)
		);

		if (passwordEncoder.matches(request.password(), member.getPassword())) {
			UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(request.email(), request.password());

			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String accessToken = tokenProvider.createToken(authentication, member);
			return new MemberSignInResponseDto(accessToken);
		}
		throw new CustomException(MemberErrorCode.NOT_EXIST_USER);
	}

}
