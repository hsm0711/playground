package com.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.member.model.BaseResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(UnsupportedJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse unsupportedJwtException(Exception e) {
		return new BaseResponse("UnsupportedJwtException");
	}

	@ExceptionHandler(MalformedJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse malformedJwtException(Exception e) {
		return new BaseResponse("MalformedJwtException");
	}

	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse expiredJwtException(Exception e) {
		return new BaseResponse("ExpiredJwtException");
	}

	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse signatureException(Exception e) {
		return new BaseResponse("SignatureException");
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public BaseResponse httpRequestMethodNotSupportedException(Exception e) {
		return new BaseResponse("HttpRequestMethodNotSupportedException");
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public BaseResponse noHandlerFoundException(Exception e) {
		return new BaseResponse("NoHandlerFoundException");
	}
}
