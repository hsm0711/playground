package com.member.api.member.model;

import com.member.annotation.Secret;
import com.member.model.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "SignRequest", description= "회원 가입 시 요청 데이터")
@Getter
@Setter
public class SignUpRequest extends BaseDto {

	@Schema(description = "사용자ID", defaultValue = "", example = "hong12")
	private String userId;

	@Secret
	@Schema(description = "비밀번호", defaultValue = "", example = "1111")
	private String password;

	@Schema(description = "이름", defaultValue = "", example = "홍길동")
	private String name;

	@Schema(description = "이메일", defaultValue = "", example = "emailId@gmail.com")
	private String email;
}
