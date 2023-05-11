package com.member.api.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.api.member.model.LoginRequest;
import com.member.api.member.model.LoginResponse;
import com.member.api.member.model.MyInfoResponse;
import com.member.api.member.model.SignRequest;
import com.member.api.member.model.SignResponse;
import com.member.api.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원", description = "회원 기능을 제공하는 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	/*
	 * 회원가입
	 */
	@Operation(summary = "회원가입", description = "회원을 가입하기 위한 메소드")
	@PostMapping("/public/signup")
	public ResponseEntity<SignResponse> createMember(@RequestBody SignRequest req) {
		return ResponseEntity.ok(memberService.createMember(req));
	}

	/*
	 * 로그인
	 */
	@Operation(summary = "로그인", description = "로그인을 진행하기 위한 메소드")
	@PostMapping("/public/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
		return ResponseEntity.ok(memberService.login(req));
	}

	/*
	 * 내 정보 조회
	 */
	@Operation(summary = "내 정보 조회", description = "본인의 정보를 조회하기 위한 메소드")
	@GetMapping("/api/me")
	public ResponseEntity<MyInfoResponse> myInfo(@RequestHeader(value = "Authorization") String token) {
		return ResponseEntity.ok(memberService.myInfo(token));
	}

}
