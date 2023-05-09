package com.member.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.net.HttpHeaders;
import com.member.exception.CustomException;
import com.member.utils.JwtTokenUtil;
import com.member.utils.MessageUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String username = "", token = "";

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) { // Bearer 토큰 파싱
			token = authorizationHeader.substring(7); // jwt token 파싱
			username = JwtTokenUtil.getUsernameFromToken(token); // username 얻어오기
		} else {
			filterChain.doFilter(request, response);
			return;
		}
		// 현재 SecurityContextHolder 에 인증객체가 있는지 확인
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// 토큰 유효여부 확인
			log.info("JWT Filter token = {}", token);
			if (JwtTokenUtil.isValidToken(token)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						username, null, List.of(new SimpleGrantedAuthority("USER")));

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		parseJwtToken(authorizationHeader);
		filterChain.doFilter(request, response);

	}

	public Claims parseJwtToken(String authorizationHeader) {
		validationAuthorizationHeader(authorizationHeader);
		String token = extractToken(authorizationHeader);
		try {
			return Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
		} catch(Exception e) {
			throw new CustomException(MessageUtils.NOT_VERIFICATION_TOKEN);
		}
	}

	private void validationAuthorizationHeader(String header) {
		if (header == null || !header.startsWith("Bearer ")) {
			throw new CustomException(MessageUtils.INVALID_TOKEN_BEARER);
		}
	}

	private String extractToken(String authorizationHeader) {
		return authorizationHeader.substring("Bearer ".length());
	}

}
