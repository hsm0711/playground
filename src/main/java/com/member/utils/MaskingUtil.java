package com.member.utils;

import org.apache.commons.lang3.StringUtils;

import com.member.constants.MemberConstants;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class MaskingUtil {

	/**
	 * 주민/외국인등록번호 뒷자리 둘째자리부터 마스킹
	 * 올바르지 않은 문자열의 경우 빈 문자열("")을 반환
	 *
	 * @param number - 주민/외국인등록번호 문자열
	 * @return String -  뒷자리 둘째자리 부터 마스킹 처리된 문자열
	 */
	public static String residentForeignerRegistrationNumber(String number) {
		if (StringUtils.isBlank(number) || !number.matches(MemberConstants.RegexPattern.RESIDENT_FOREIGNER_REGISTRATION_NUMBER)) {
			return "";
		}

		return number.replaceAll("^(\\d{6}-*\\d)\\d{6}$", "$1******");
	}
}
