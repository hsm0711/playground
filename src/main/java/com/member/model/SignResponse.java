package com.member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "SignResponse", description= "회원 가입 시 응 데이터")
@Data
public class SignResponse extends BaseResponse {
	
	@Schema(description = "사용자ID", defaultValue = "", example = "hong12")
	private String userId; //아이디
	
	@Schema(description = "이름", defaultValue = "", example = "홍길동")
	private String name; //이름
}
