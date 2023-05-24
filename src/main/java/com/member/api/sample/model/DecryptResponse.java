package com.member.api.sample.model;

import com.member.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "DecryptResponse", description = "복호화 응답 데이터")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class DecryptResponse extends BaseDto {

  @Schema(description = "복호화 요청 문자열", example = "qwer1234/5uKD75cYuiNPZReQp36J..")
  private String inputStr;

  @Schema(description = "복호화 결과 문자열", example = "hong12/5uKD75cYuiNPZReQp36J..")
  private String resultStr;
}
