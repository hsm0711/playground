package com.member.api.member.model;

import com.member.annotation.Secret;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "SignRequest", description= "회원 가입 시 요청 데이터")
@Getter
@Setter
public class SignRequest {

	@Schema(description = "사용자ID", defaultValue = "", example = "hong12")
	private String userId; //아이	디

	@Secret
	@Schema(description = "비밀번호", defaultValue = "", example = "1111")
	private String password; //패스워드

	@Schema(description = "이름", defaultValue = "", example = "홍길동")
	private String name; //이름

	@Secret
	@Schema(description = "주민등록번호", defaultValue = "", example = "900101-1234567")
	private String regNo; //주민등록번호
}
