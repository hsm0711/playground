package com.member.api.sample.model;

import com.member.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "PasswordCompareRequest", description = "평문, 암호화 비교 요청 데이터")
@Getter
@Setter
public class PasswordCompareRequest extends BaseDto {

  @Schema(description = "비교 할 평문 비밀번호", required = true, nullable = false, example = "비밀번호1234!@#$")
  private String plainText;

  @Schema(description = "비교 할 암호화 비밀번호", required = true, nullable = false,
      example = "JDJhJDEwJHFsSlR6VGpIcjcwSUtZbjBlZXJkRC56SEZVTGVhY2s2a3NNOVVpZTdYWXdwWWx2UnJMUkhX")
  private String encryptedText;

}
