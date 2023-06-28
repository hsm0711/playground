package com.playground.api.sample.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
import com.playground.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisTemplateEntity extends BaseEntity {

  @Id
  private String id;

  private String name;

  private Integer age;

  private LocalDateTime createdAt;
}
