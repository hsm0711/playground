package com.member.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageUtils {
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "FAIL";

	public static final String ACCESS_NOT_USER = "회원 가입 대상이 아닙니다.";
	public static final String INVALID_USER = "회원 정보가 존재하지 않습니다.";
	public static final String DUPLICATE_USER = "이미 가입 되어있는 회원입니다.";
	public static final String INVALID_USER_ID = "ID를 확인해주세요.";
	public static final String INVALID_NAME = "이름을 확인해주세요.";
	public static final String INVALID_PASSWORD = "비밀번호를 확인해주세요.";
	public static final String INVALID_EMAIL = "이메일을 확인해주세요.";
	public static final String INVALID_TOKEN = "토큰을 확인해주세요.";
	public static final String NOT_EXIST_DATA = "데이터가 존재하지 않습니다.";
	public static final String NOT_VERIFICATION_TOKEN = "토큰이 유효하지 않습니다.";
	public static final String INVALID_TOKEN_BEARER = "토큰이 Bearer 방식이 아닙니다.";
}
