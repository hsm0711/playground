package com.member.api.sample.model;

import com.member.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "EncryptResponse", description = "암호화 응답 데이터")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class PasswordResponse extends BaseDto {

  @Schema(description = "암호화 요청 문자열", example = "비밀번호1234!@#$")
  private String inputStr;

  @Schema(description = "암호화 결과 문자열", example = "JDJhJDEwJHFsSlR6VGpIcjcwSUtZbjBlZXJkRC56SEZVTGVhY2s2a3NNOVVpZTdYWXdwWWx2UnJMUkhX")
  private String resultStr;
}
