package com.member.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.member.api.member.model.Member;
import com.member.constants.MemberConstants;
import com.member.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class JwtTokenUtil {
	private static final String USER_ID = "userId";

	// 토큰 생성
	public static String createToken(String userId, String name) {

		Map<String, Object> headers = new HashMap<>();

		headers.put("typ", "JWT");
		headers.put("alg", "HS256");

		Map<String, Object> payloads = new HashMap<>();

		// API 용도에 맞게 properties로 관리하여 사용하는것을 권장한다.
		payloads.put(USER_ID, userId);
		payloads.put("name", name);

		// 토큰 유효 시간 (30분)
		Long expiredTime = 1000 * 60 * 30L;

		Date ext = new Date(); // 토큰 만료 시간
		ext.setTime(ext.getTime() + expiredTime);

		return Jwts.builder().setHeader(headers) // Headers 설정
				.setClaims(payloads) // Claims 설정
				.setIssuer("issuer") // 발급자
				.setSubject("auth") // 토큰 용도
				.setExpiration(ext) // 토큰 만료 시간 설정
				.signWith(SignatureAlgorithm.HS256, "secretKey") // HS256과 Key로 Sign
				.compact(); // 토큰 생성
	}

	/**
	 * 토큰 만료여부 확인
	 */
	public static Boolean isValidToken(String token) {
		return !isTokenExpired(token);
	}

	/**
	 * 토큰의 Claim 디코딩
	 */
	private static Claims getAllClaims(String token) {
		try {
			return Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
		} catch (Exception e) {
			throw new CustomException(MessageUtils.NOT_VERIFICATION_TOKEN);
		}
	}

	/**
	 * Claim 에서 username 가져오기
	 */
	public static String getUsernameFromToken(String token) {
		try {
			String name = String.valueOf(getAllClaims(token).get("name"));
			log.debug("getUsernameFromToken subject = {}", name);
			return name;
		} catch (NullPointerException e) {
			throw new CustomException(MessageUtils.NOT_VERIFICATION_TOKEN);
		}
	}

	/**
	 * Claim 에서 user_id 가져오기
	 */
	public static String getUserIdFromToken(String token) {
		String id = String.valueOf(getAllClaims(token).get(USER_ID));
		log.debug("getUserIdFromToken subject = {}", id);
		return id;
	}

	/**
	 * 토큰 만료기한 가져오기
	 */
	public static Date getExpirationDate(String token) {
		Claims claims = getAllClaims(token);
		return claims.getExpiration();
	}

	/**
	 * 토큰이 만료되었는지
	 */
	private static boolean isTokenExpired(String token) {
		return getExpirationDate(token).before(new Date());
	}

	public static Member autholriztionCheckUser(String token) {
		String authorization = token;
		Member rs = new Member();

		if (StringUtils.hasText(token) && token.startsWith(MemberConstants.TOKEN_PREFIX)) {
			authorization = authorization.replaceAll(MemberConstants.TOKEN_PREFIX, "");
			log.debug(">>> authorization : {}", authorization);
			Claims claims = getAllClaims(authorization);

			rs.setName((String) claims.get("name"));
			rs.setUserId((String) claims.get(USER_ID));
			return rs;
		}

		return rs;
	}
}
