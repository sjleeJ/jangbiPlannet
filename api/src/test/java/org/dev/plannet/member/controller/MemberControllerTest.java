package org.dev.plannet.member.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.dev.plannet.member.Member;
import org.dev.plannet.member.MemberRepository;
import org.dev.plannet.member.dto.signup.MemberSignUpDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test@gmail.com","12341234","테스트","01023452345");
        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberSignUpDto)))
                .andDo(print())
                .andExpect(status().isOk());

        Member memberResult = memberRepository.findByEmail("test@gmail.com");
        System.out.println("member:"+memberResult.toString());
        assertAll(
                () -> assertThat(memberResult.getEmail()).isEqualTo("test@gmail.com"),
                () -> assertThat(memberResult.getNickname()).isEqualTo("테스트"),
                () -> assertThat(memberResult.getPhone()).isEqualTo("01023452345")
        );
    }


}
