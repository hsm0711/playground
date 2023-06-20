package com.playground.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class PlaygroundConstants {
  public static final String TOKEN_PREFIX = "Bearer ";

  /**
   * 정규식 패턴
   */
  @UtilityClass
  public final class RegexPattern {
    /**
     * 주민등록번호
     */
    public static final String RESIDENT_REGISTRATION_NUMBER = "\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])[-]*[1-4]\\d{6}";

    /**
     * 외국인등록번호
     */
    public static final String FOREIGNER_REGISTRATION_NUMBER = "\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])[-]*[5-8]\\d{6}";

    /**
     * 주민등록번호 + 외국인등록번호
     */
    public static final String RESIDENT_FOREIGNER_REGISTRATION_NUMBER = "\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])[-]*[1-8]\\d{6}";
  }
}
