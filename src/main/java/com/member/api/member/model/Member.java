package com.member.api.member.model;

import com.member.annotation.Secret;
import com.member.model.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member extends BaseDto {

	private String userId; //아이디

	@Secret
	private String password; //패스워드

	private String name; //이름

	@Secret
	private String regNo; //주민등록번호
}
