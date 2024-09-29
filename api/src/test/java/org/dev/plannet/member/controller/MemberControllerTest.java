package org.dev.plannet.member.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.dev.plannet.IntegrationMockMvcAndRestDocsTest;
import org.dev.plannet.member.Member;
import org.dev.plannet.member.MemberRepository;
import org.dev.plannet.member.dto.signup.MemberSignUpDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@IntegrationMockMvcAndRestDocsTest
public class MemberControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MemberRepository memberRepository;

	@Test
	@DisplayName("회원가입성공")
	public void signUpSuccess() throws Exception {
		MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test1@gmail.com", "12341234", "테스트1", "01023452345");
		mockMvc.perform(post("/members")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(memberSignUpDto)))
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document("sign-up",
				requestFields(
					fieldWithPath("email").description("사용자의 이메일"),
					fieldWithPath("password").description("사용자의 비밀번호"),
					fieldWithPath("nickname").description("사용자의 닉네임"),
					fieldWithPath("phone").description("사용자의 전화번호")
				),
				responseFields(
					fieldWithPath("result").description("API 호출 결과"),
					fieldWithPath("body.email").description("등록된 사용자의 이메일"),
					fieldWithPath("body.nickname").description("등록된 사용자의 닉네임"),
					fieldWithPath("body.phone").description("등록된 사용자의 전화번호")
				)
			));

		Member memberResult = memberRepository.findByEmail("test1@gmail.com").orElseThrow();
		System.out.println("member:" + memberResult.toString());
		assertAll(
			() -> assertThat(memberResult.getEmail()).isEqualTo("test1@gmail.com"),
			() -> assertThat(memberResult.getNickname()).isEqualTo("테스트1"),
			() -> assertThat(memberResult.getPhone()).isEqualTo("01023452345")
		);
	}

}
