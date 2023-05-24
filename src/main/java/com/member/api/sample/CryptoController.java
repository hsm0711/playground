package com.member.api.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.member.api.sample.model.CryptoRequest;
import com.member.api.sample.model.CryptoResponse;
import com.member.model.BaseResponse;
import com.member.utils.CryptoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Crypto 샘플 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/public/sample/crypto")
public class CryptoController {
  /*
   * 암호화 - AES
   */
  @ApiOperation(value = "암호화 - AES", notes = "AES 암호화")
  @PostMapping("/encrypt")
  public ResponseEntity<BaseResponse<CryptoResponse>> encrypt(@RequestBody CryptoRequest request) {
    String input = request.getPlainText();

    return ResponseEntity.ok(new BaseResponse<>(CryptoResponse.builder().inputStr(input).resultStr(CryptoUtil.encrypt(input)).build()));
  }

  /*
   * 복호화 - AES
   */
  @ApiOperation(value = "복호화 - AES", notes = "AED 복호화")
  @PostMapping("/decrypt")
  public ResponseEntity<BaseResponse<CryptoResponse>> decrypt(@RequestBody CryptoRequest request) {
    String input = request.getEncryptedText();

    return ResponseEntity.ok(new BaseResponse<>(CryptoResponse.builder().inputStr(input).resultStr(CryptoUtil.decrypt(input)).build()));
  }

  /*
   * 비밀번호 단방향 암호화
   */
  @ApiOperation(value = "비밀번호 단방향 암호화", notes = "비밀번호 단방향 암호화")
  @PostMapping("/password")
  public ResponseEntity<BaseResponse<CryptoResponse>> password(@RequestBody CryptoRequest request) {
    String input = request.getPlainText();

    return ResponseEntity.ok(new BaseResponse<>(CryptoResponse.builder().inputStr(input).resultStr(CryptoUtil.encodePassword(input)).build()));
  }

  /*
   * 평문, 암호화 비밀번호 비교
   */
  @ApiOperation(value = "평문, 암호화 비밀번호 비교", notes = "평문과 암호화된 비밀번호가 동일한 값인지 비교")
  @PostMapping("/password-compare")
  public ResponseEntity<BaseResponse<Boolean>> passwordCompare(@RequestBody CryptoRequest request) {
    return ResponseEntity.ok(new BaseResponse<>(CryptoUtil.comparePassword(request.getPlainText(), request.getEncryptedText())));
  }
}
