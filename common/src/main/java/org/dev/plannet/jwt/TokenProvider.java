package org.dev.plannet.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.dev.plannet.error.TokenErrorCode;
import org.dev.plannet.exception.ApiException;
import org.dev.plannet.member.Member;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    protected static final String AUTHORITIES_KEY = "auth";
    protected static final String USERID = "id";
    protected static final String EMAIL = "email";
    final long tokenValidityInMilliseconds;
    protected Key key;
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_PREFIX = "Bearer ";

    public TokenProvider(
            @Value("${jwt.access.token.secret}") String secret,
            @Value("${jwt.access.token.seconds}") long tokenValidityInSeconds) {
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 토큰을 추출하는 메서드
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public String createToken(Authentication authentication, Member user) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(USERID, user.getId())
                .claim(EMAIL, user.getEmail())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new ApiException(TokenErrorCode.INVALID_SIGNATURE, "잘못된 서명");
        } catch (ExpiredJwtException e) {
            throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, "만료된 토큰");
        } catch (UnsupportedJwtException e) {
            throw new ApiException(TokenErrorCode.UNSUPPORTED_JWT, "만료된 토큰");
        } catch (IllegalArgumentException e) {
            throw new ApiException(TokenErrorCode.ILLEGAL_TOKEN, "만료된 토큰");
        }
    }
}