package com.member.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE ;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.CellType;

/**
 * 엑셀 다운로드 시 다운로드 될 Sheet, 컬럼 정보를 포함하는 annotaion
 */
@Target({TYPE, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelDown {
	/**
	 * 해당 Sheet의 이름을 설정한다.
	 *
	 * scope: Dto Class
	 * default: Sheet1
	 */
	String sheetName() default "Sheet1";

	/**
	 * 해당 Column의 해더 이름을 설정한다.
	 *
	 * scope: Field
	 */
	String headerName();

	/**
	 * 해당 Column의 withd를 설정한다.
	 *
	 * scope: Field
	 */
	int width() default -1;

	/**
	 * 해당 Column이 표시될 순서를 설정한다.
	 * 순서가 지정되지 않은 경우 order설정 된 Column들 뒤에 VO/DTO에 선언한 순서로 표시된다.
	 *
	 * scope: Field
	 */
	int order() default Integer.MAX_VALUE;

	/**
	 * 해당 Cell의 타입을 설정한다.
	 *
	 * scope: Field
	 */
	CellType cellType();

	/**
	 * 해당 Cell Data의 Format을 설정한다.
	 *
	 * scope: Field
	 * ex: "#,##", "yyyy-MM-dd"
	 */
	String dataFormat() default "";
}
