package com.member.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.annotation.Secret;

public class BaseDto {

	@Override
	public String toString() {
		// TODO annotation 만들어서 해당 annotation이 있으면 민감정보로 판단하고 로그에 마스킹 처리 되도록
		Map<String, Object> map = new HashMap<>();
		Field[] fields = FieldUtils.getAllFields(getClass());
		ObjectMapper mapper = new ObjectMapper();

		if (ObjectUtils.isNotEmpty(fields)) {
			for(Field field : fields) {
				String fieldName = field.getName();
				String fieldValue = "";

				Annotation annotation = field.getAnnotation(Secret.class);

				try {
					fieldValue = StringUtils.defaultString(Objects.toString(org.springframework.security.util.FieldUtils.getFieldValue(field, fieldName)));
				} catch (IllegalAccessException e) {
					// Do nothing
				}

				// 민감정보 마스킹
				if (annotation != null) {

				}

				map.put(fieldName, fieldValue);
			}
		}

		try {
			return mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			return getClass().getName() + "@" + Integer.toHexString(hashCode());
		}
	}
}