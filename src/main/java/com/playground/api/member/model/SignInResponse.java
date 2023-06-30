package com.playground.api.member.model;

import com.playground.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(name = "LoginResponse", description = "로그인 응답 데이터")
public class SignInResponse extends BaseDto {

  @Schema(description = "토큰", example = "JWT 토큰")
  private final String token;

  @Builder
  public SignInResponse(String token) {
    super();
    this.token = token;
  }
}
