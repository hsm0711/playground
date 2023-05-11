package com.member.constants;

import com.member.exception.CustomException;

public final class CacheType {
	private CacheType() {
		// TODO 메시지 체계 정리 후 메시징 처리 수정
		throw new CustomException("Utility class");
	}

	public static final String ONE_MINUTES = "ONE_MINUTES";
	public static final String ONE_HOUR = "ONE_HOUR";
	public static final String TEN_MINUTES = "TEN_MINUTES";
}
