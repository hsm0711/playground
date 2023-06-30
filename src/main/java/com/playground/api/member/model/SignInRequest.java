package com.playground.api.member.model;

import jakarta.validation.constraints.NotBlank;
import com.playground.annotation.Secret;
import com.playground.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "LoginRequest", description = "로그인 요청 데이터")
@Getter
@Setter
public class SignInRequest extends BaseDto {

  @NotBlank(message = "ID는 필수 값 입니다.")
  @Schema(description = "사용자ID", example = "hong12")
  private String userId;

  @NotBlank(message = "비밀번호는 필수 값 입니다.")
  @Secret
  @Schema(description = "비밀번호", example = "1111")
  private String password;
}
