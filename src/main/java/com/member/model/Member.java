package com.member.model;

import lombok.Data;

@Data
public class Member {

	private String userId; //아이디
	
	private String password; //패스워드
	
	private String name; //이름
	
	private String regNo; //주민등록번호

}
