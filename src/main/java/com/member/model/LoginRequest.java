package com.member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "LoginRequest", description= "로그인 요청 데이터")
@Data
public class LoginRequest{

	@Schema(description = "사용자ID", defaultValue = "", example = "hong12")
	private String userId; //아이디
	
	@Schema(description = "비밀번호", defaultValue = "", example = "1111")
	private String password; //패스워드
}
