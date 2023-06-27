package com.playground.api.sample.model;

import javax.validation.constraints.NotBlank;
import com.playground.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "PasswordRequest", description = "암호화 요청 데이터")
@Getter
@Setter
public class PasswordRequest extends BaseDto {

  @NotBlank(message = "비밀번호는 필수 값 입니다.")
  @Schema(description = "암호화 할 비밀번호", requiredMode = RequiredMode.REQUIRED, nullable = false, example = "비밀번호1234!@#$")
  private String plainText;

}
