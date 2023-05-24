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
public class EncryptResponse extends BaseDto {

  @Schema(description = "암호화 요청 문자열", example = "테스트문자열123!@#")
  private String inputStr;

  @Schema(description = "암호화 결과 문자열", example = "4+uZCvlFjkikD3+E3ESZF2uYpf3/ZpcOEd2pksSvvFr3VtzFU780JNyWT7rcHHRj")
  private String resultStr;
}
