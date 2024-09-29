package org.dev.plannet.security;

import java.io.IOException;
import java.util.stream.Stream;

import org.dev.plannet.jwt.JwtAccessDeniedHandler;
import org.dev.plannet.jwt.JwtAuthenticationEntryPoint;
import org.dev.plannet.jwt.JwtSecurityConfig;
import org.dev.plannet.jwt.TokenProvider;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final CorsFilter corsFilter;

	//권한 확인을 하지 않는 uri
	private static final String[] PERMIT_ALL_PATTERNS = new String[] {
		"/v3/api-docs/**",
		"/swagger*/**",
		"/swagger-ui/**",
		"/open-api/**",
	};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			// token을 사용하는 방식이기 때문에 csrf를 disable합니다.
			.csrf(AbstractHttpConfigurer::disable)
			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests(it -> {
				it.requestMatchers(
						//리소스는 모두 허용
						PathRequest.toStaticResources().atCommonLocations()
					).permitAll()
					.requestMatchers(
						Stream.of(PERMIT_ALL_PATTERNS)
							.map(AntPathRequestMatcher::antMatcher)
							.toArray(AntPathRequestMatcher[]::new)
					).permitAll()
					//그외는 모두 인증
					.anyRequest().authenticated();
			})
			.exceptionHandling(exceptionHandling -> exceptionHandling
				.accessDeniedHandler(jwtAccessDeniedHandler)
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			)

			// 세션을 사용하지 않기 때문에 STATELESS로 설정
			.sessionManagement(sessionManagement ->
				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.with(new JwtSecurityConfig(tokenProvider), customizer -> {
			});

		return http.build();

	}

	@Bean
	public SimpleUrlAuthenticationSuccessHandler successHandler() {
		return new SimpleUrlAuthenticationSuccessHandler("/member") {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
				super.onAuthenticationSuccess(request, response, authentication);
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();  // BCryptPasswordEncoder 사용
	}
}
