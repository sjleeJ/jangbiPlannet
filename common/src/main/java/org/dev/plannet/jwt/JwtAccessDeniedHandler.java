package org.dev.plannet.jwt;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * AccessDeniedHandler: AccessDeniedHandler는 Spring Security에서 인증된 사용자가 특정 리소스에 접근할 권한이 없을 때 처리하는 인터페이스
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException {
		//필요한 권한이 없이 접근하려 할때 403
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}
}