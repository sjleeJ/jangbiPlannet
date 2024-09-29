package org.dev.plannet.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security에서 보안 설정을 맞춤화할 때 사용하는 추상 클래스입니다.
 * 이 클래스를 상속받아 Spring Security의 설정을 커스터마이징할 수 있습니다
 * Spring Security 설정을 확장하여, **인증(Authentication)**과 인가(Authorization) 등의 보안 요소를 제어하고 설정할 수 있습니다.
 */
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private TokenProvider tokenProvider;

	public JwtSecurityConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void configure(HttpSecurity http) {
		JwtFilter customFilter = new JwtFilter(tokenProvider);
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
}