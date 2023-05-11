package com.member.api.sample.entity;

import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import com.member.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

//@RedisHash(value = keyspace값, timeToLive = 만료시간(초))
@RedisHash(value = "RedisEntity")
@Getter
@Setter
public class RedisEntity extends BaseEntity {

	@Id
	private String id;

	private String name;

	private Integer age;

	private LocalDateTime createdAt;
}
