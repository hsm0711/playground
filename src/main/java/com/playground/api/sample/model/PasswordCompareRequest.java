package com.playground.api.sample.model;

import com.playground.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "PasswordCompareRequest", description = "평문, 암호화 비교 요청 데이터")
@Getter
@Setter
public class PasswordCompareRequest extends BaseDto {

  @Schema(description = "비교 할 평문 비밀번호", requiredMode = RequiredMode.REQUIRED, nullable = false, example = "비밀번호1234!@#$")
  private String plainText;

  @Schema(description = "비교 할 암호화 비밀번호", requiredMode = RequiredMode.REQUIRED, nullable = false,
      example = "JDJhJDEwJHFsSlR6VGpIcjcwSUtZbjBlZXJkRC56SEZVTGVhY2s2a3NNOVVpZTdYWXdwWWx2UnJMUkhX")
  private String encryptedText;

}
