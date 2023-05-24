package com.member.api.sample.model;

import javax.validation.constraints.NotBlank;
import com.member.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "EncryptRequest", description = "암호화 요청 데이터")
@Getter
@Setter
public class EncryptRequest extends BaseDto {

  @NotBlank(message = "암호화 할 문자열은 필수 값 입니다.")
  @Schema(description = "암호화 할 문자열", required = true, nullable = false, example = "테스트문자열123!@#")
  private String plainText;

}
