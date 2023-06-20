package com.playground.api.member.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.playground.annotation.Secret;
import com.playground.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "SignRequest", description = "회원 가입 시 요청 데이터")
@Getter
@Setter
public class SignUpRequest extends BaseDto {

  @NotBlank(message = "ID는 필수 값 입니다.")
  @Schema(description = "사용자ID", defaultValue = "", example = "hong12")
  private String userId;

  @NotBlank(message = "비밀번호는 필수 값 입니다.")
  @Secret
  @Schema(description = "비밀번호", defaultValue = "", example = "1111")
  private String password;

  @NotBlank(message = "이름은 필수 값 입니다.")
  @Schema(description = "이름", defaultValue = "", example = "홍길동")
  private String name;

  @Email(message = "이메일 형식을 확인해 주세요.")
  @NotBlank(message = "이메일은 필수 값 입니다.")
  @Schema(description = "이메일", defaultValue = "", example = "emailId@gmail.com")
  private String email;
}
