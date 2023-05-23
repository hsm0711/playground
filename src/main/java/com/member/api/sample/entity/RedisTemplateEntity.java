package com.member.api.sample.entity;

import java.time.LocalDateTime;

import javax.persistence.Id;

import com.member.entity.BaseEntity;

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
