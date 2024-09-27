package org.dev.plannet.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * GenericFilterBean:  Spring Security와 통합하기 위한 추가적인 기능을 제공합니다. 주로 필터 체인에서 동작하는 커스텀 필터를 구현
 * 이를 사용해 필터 체인에서 요청을 가로채서 인증, 인가, 로깅 등의 작업을 수행할 수 있습니다.
 * 주로 JWT 인증이나 로깅, 헤더 검사 등의 요청을 처리할 때 사용됩니다.
 * 필터는 필터 체인에 등록되고, 순서를 제어할 수 있습니다.
 */
@Slf4j
@AllArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private TokenProvider tokenProvider;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String jwt = resolveToken(request);
        String path = request.getRequestURI();
        // 경로에 대해 필터를 건너뛰도록 설정
        if (path.startsWith("/swagger-ui/")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger")
                || path.startsWith("/open-api")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}",path);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.split(" ")[1].trim();
        }

        return null;
    }
}
