package com.member.constants;

import com.member.exception.CustomException;

public final class MemberConstants {
	private MemberConstants() {
		// TODO 메시지 체계 정리 후 메시징 처리 수정
		throw new CustomException("Utility class");
	}

	public static final String TOKEN_PREFIX = "Bearer ";
}
