package com.playground.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MaskingUtilTest {
  @DisplayName("주민/외국인등록번호 마스킹 테스트")
  @Test
  void residentForeignerRegistrationNumber() {
    // Given
    String number = "900711-1234567";
    String number2 = "9007111234567";

    // When
    String maskedNumber = MaskingUtil.residentForeignerRegistrationNumber(number);
    String maskedNumber2 = MaskingUtil.residentForeignerRegistrationNumber(number2);

    log.debug(maskedNumber);
    log.debug(maskedNumber2);

    // Then
    assertEquals("900711-1******1", maskedNumber); // 테스트 실패
    assertEquals("9007111******", maskedNumber2);
  }
}
