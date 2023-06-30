package com.playground.api.member.model;

import com.playground.annotation.Secret;
import com.playground.model.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "MemberInfoResponse", description = "내 정보 조회 응답 데이터")
@Getter
@Setter
public class MemberInfoResponse extends BaseDto {

  @Schema(description = "사용자ID", example = "test1")
  private String userId;

  @Secret
  @Schema(description = "비밀번호", example = "1234")
  private String password;

  @Schema(description = "이름", example = "홍길동")
  private String name;

  @Schema(description = "이메일", example = "emailId@gmail.com")
  private String email;
}
