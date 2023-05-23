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
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/public/sample/crypto")
public class CryptoController {
  @PostMapping("/encrypt")
  public ResponseEntity<BaseResponse<CryptoResponse>> encrypt(@RequestBody CryptoRequest request) {
    String input = request.getPlainText();

    return ResponseEntity.ok(new BaseResponse<>(CryptoResponse.builder().inputStr(input).resultStr(CryptoUtil.encrypt(input)).build()));
  }

  @PostMapping("/decrypt")
  public ResponseEntity<BaseResponse<CryptoResponse>> decrypt(@RequestBody CryptoRequest request) {
    String input = request.getEncryptedText();

    return ResponseEntity.ok(new BaseResponse<>(CryptoResponse.builder().inputStr(input).resultStr(CryptoUtil.decrypt(input)).build()));
  }

  @PostMapping("/password")
  public ResponseEntity<BaseResponse<CryptoResponse>> password(@RequestBody CryptoRequest request) {
    String input = request.getPlainText();

    return ResponseEntity.ok(new BaseResponse<>(CryptoResponse.builder().inputStr(input).resultStr(CryptoUtil.encodePassword(input)).build()));
  }

  @PostMapping("/password-compare")
  public ResponseEntity<BaseResponse<Boolean>> passwordCompare(@RequestBody CryptoRequest request) {
    return ResponseEntity.ok(new BaseResponse<>(CryptoUtil.comparePassword(request.getPlainText(), request.getEncryptedText())));
  }
}
