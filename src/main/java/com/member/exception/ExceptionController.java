package com.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.member.model.BaseResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(UnsupportedJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse unsupportedJwtException(Exception e) {
		e.printStackTrace();
		return new BaseResponse("UnsupportedJwtException");
	}

	@ExceptionHandler(MalformedJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse malformedJwtException(Exception e) {
		e.printStackTrace();
		return new BaseResponse("MalformedJwtException");
	}

	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse expiredJwtException(Exception e) {
		e.printStackTrace();
		return new BaseResponse("ExpiredJwtException");
	}

	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse signatureException(Exception e) {
		e.printStackTrace();
		return new BaseResponse("SignatureException");
	}
}
