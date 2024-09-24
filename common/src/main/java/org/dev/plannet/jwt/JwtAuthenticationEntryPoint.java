package org.dev.plannet.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * AuthenticationEntryPoint: AuthenticationEntryPoint는 Spring Security에서 인증되지 않은 사용자가
 * 보호된 리소스에 접근하려 할 때 처리하는 인터페이스입니다.
 * 즉, 사용자가 인증되지 않은 상태로 접근했을 때, "401 Unauthorized" 에러를 처리하는 역할
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}