package com.playground.annotation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
/*
  DTO의 Field 중 민감정보를 포함하는 Field는 민감정보를 마스킹 처리 하기위해 해당 Field를 마킹하는 annotaion
 */
public @interface Secret {

}
