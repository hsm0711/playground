package com.member.api.sample.model;

import com.member.model.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "CryptoRequest", description = "암복호화 요청 데이터")
@Getter
@Setter
public class CryptoRequest extends BaseDto {

  @Schema(description = "암호화 할 문자열", defaultValue = "", example = "hong12")
  private String plainText;

  @Schema(description = "복호화 할 문자열", defaultValue = "", example = "5uKD75cYuiNPZReQp36J...")
  private String encryptedText;
}
