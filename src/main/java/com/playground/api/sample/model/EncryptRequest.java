package com.playground.api.sample.model;

import jakarta.validation.constraints.NotBlank;
import com.playground.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "EncryptRequest", description = "암호화 요청 데이터")
@Getter
@Setter
public class EncryptRequest extends BaseDto {

  @NotBlank(message = "암호화 할 문자열은 필수 값 입니다.")
  @Schema(description = "암호화 할 문자열", requiredMode = RequiredMode.REQUIRED, nullable = false, example = "테스트문자열123!@#")
  private String plainText;

}
