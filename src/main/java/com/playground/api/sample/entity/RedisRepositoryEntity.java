package com.playground.api.sample.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import com.playground.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

// @RedisHash(value = keyspace값, timeToLive = 만료시간(초))
@RedisHash(value = "RedisRepositoryEntity")
@Getter
@Setter
public class RedisRepositoryEntity extends BaseEntity {

  @Id
  private String id;

  private String name;

  private Integer age;

  private LocalDateTime createdAt;
}
