package com.playground.api.sample.model;

import javax.validation.constraints.NotBlank;
import com.playground.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "DecryptRequest", description = "복호화 요청 데이터")
@Getter
@Setter
public class DecryptRequest extends BaseDto {

  @NotBlank(message = "복호화 할 문자열은 필수 값 입니다.")
  @Schema(description = "복호화 할 문자열", requiredMode = RequiredMode.REQUIRED, nullable = false,
      example = "4+uZCvlFjkikD3+E3ESZF2uYpf3/ZpcOEd2pksSvvFr3VtzFU780JNyWT7rcHHRj")
  private String encryptedText;
}
